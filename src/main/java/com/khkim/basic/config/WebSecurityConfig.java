package com.khkim.basic.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.khkim.basic.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// ! desription :인증 및 인가  처리와 관련된  여러 설정을 지정하는 클래스 //
// ! @Configurable - spring 설정 변경이 가능한 클래스로 지정하는 어노테이션 //
@Configurable
// ! @EnableWebSecurity security 설정 변경 클래스로 지정하는 어노테이션  //
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    // description : Security 설정 변경 메소드 작성 //
    // 보안관 관련된 작업 묶음 
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
     
        httpSecurity
        // description : Cors정책을 기본정책으로 사용 (CorsConfig룰 따르게 함) //
            .cors().and() 
        // description :  csrf보안설정을 사용하지 않음    //
            .csrf().disable()
        // description :  httpBasic 인증 을 사용하지 않음     //
            .httpBasic().disable()
        // description : 세션 생성 전략을  세션을 생성하지 않음으로 설정 //
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        // description: 어떤 요청에 대한 인증을 수행할 지 지정하는 설정 //
            .authorizeRequests()
        // description 특정 요청 지정 (url 패턴에 따른 지정,    , http method + url에 따른 지정) //
        // description  url 패턴에 따른 지정(/user 로 시작하는 모든 요청에 대하여 허용)
            .antMatchers( "/user/**","/sign_in").permitAll()
        // description  http 메서드 에 따른 지정 (모든  get 요청에 대해 허용 , 인증없이 사용가능) //
            .antMatchers(HttpMethod.GET).permitAll()
        // description : http + method url 패턴에 따른 지정 (post /board 로 시작하는 모든 요청에 대하여 허용, 인증 없이 사용가능)   //
            .antMatchers(HttpMethod.POST, "/board/**" ).permitAll()
        // description : 나머지 모든 요청에 대하여 인증을 수정 //
            .anyRequest().authenticated().and()            
        //  description 인증 과정에서 발생한 예외를 직접 처리
            .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPoint());

            
        httpSecurity.addFilterBefore( jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);    

        return httpSecurity.build();

    }
    
}

// description : 인증 과정에 발생하는 에외에 대한 response를  직접 처리하는 클래스
// description : AuthenticationEntryPoint 인터페이스 구축 // 
class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        
        // description : reponse 컨텐츠타입을 json으로 지정
        response.setContentType("applyication/json");     
        // description  : reponse http 상태 코드를 지정    
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // description : response의 바디 작성
        response.getWriter().write("{ \"code\":\"AF\", \"message\":\"Authorization Failed\"}");  
        
        
        
        

    }


}