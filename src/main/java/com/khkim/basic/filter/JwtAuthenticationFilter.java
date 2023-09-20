package com.khkim.basic.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.khkim.basic.provider.JwtProvider;


import lombok.RequiredArgsConstructor;

// description: Bearer Token 인증 방식을 사용한 JWT 인증 필터 // 
// description:  requetsHeader의 Authorizaion 필드 의값을 가져와서 //
// description:  해당 토큰이 정상적인 토큰인지 확인하고 정상이 아닐경우 요청을 거부 //
// description: 정상적인 토큰일 경우 인증된 사용자의 정보를 controller 에서 사용할 수 있도록 함//

// description: OncePerRequestFilter 를 확장항 클래스를 Filter 클래스로 만듬 //  

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtProvider jwtProvider;


    // description : OncePerRequestFilter의 doFiterInternal 추상메서드를 해당 필터에서 동작할 기능을  구현 //
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
            try {
                //description : 1. Bearer Token Request Header 에 있는   Authorization JWT 추출    //
                String token = parseBearerToken(request);
                if(token == null){
                    //description : 다음 필터로 넘기 // 
                    filterChain.doFilter(request, response);
                    return;
                }

                // description : 2.  추출한 token 검증 //
                
                String  subject = jwtProvider.validate(token);
                if( subject == null){
                    filterChain.doFilter(request, response);
                    return;
                }
                // description validator에 대한 추가 정보 받는 방법 //    
                // description3. Context에 등록할 토큰 객체를 생성하는 방법 //
                AbstractAuthenticationToken authenticationToken 
                //  UsernamePasswordAuthenticationToken 사용자이름, 패스워드, 권한으로 구성되어 있는 토큰 객체
                    = new UsernamePasswordAuthenticationToken(subject, null, AuthorityUtils.NO_AUTHORITIES);      
            } catch (Exception exception) {
                exception.printStackTrace();

            }
            filterChain.doFilter(request, response);
               //인증 절차 종료
            
            
    }

    // description : Request 객체로부터 JWT를 호출하는 메서드 //
    private String parseBearerToken(HttpServletRequest request){
        // description : 1. request 객체의 header에서  Authorization 값 추출 (철자 조심)//
        String authorization =  request.getHeader("Authorization");
        // description : 전달한 문자열이 null이거나 빈문자열으로 구성되어 있으면 false 반환//
        // boolean hasAuthorization = StringUtils.hasText(authorization);
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;
        // description : 2.인증방식이 Bearer인지 확인 
        boolean IsBearer = authorization.startsWith(  "Bearer ");
        if(!IsBearer) return null;

        // description : 3 . Bearer  토큰 추출 //
        String token = authorization.substring(7);

        return token;
    }
    
}