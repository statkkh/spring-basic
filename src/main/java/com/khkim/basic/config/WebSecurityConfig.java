package com.khkim.basic.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
            .antMatchers( "/user/**").permitAll()
        // description  http 메서드 에 따른 지정 (모든  get 요청에 대해 허용) //
            .antMatchers(HttpMethod.GET).permitAll()
        // description : http + method url 패턴에 따른 지정 (post /board 로 시작하는 모든 요청에 대하여 허용)   //
            .antMatchers(HttpMethod.POST, "/board/**" ).permitAll()
        // description : 나머지 모든 요청에 대하여 인증을 수정 //
            .anyRequest().authenticated();            

    httpSecurity.addFilterBefore( jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);    

    return httpSecurity.build();
    }
    
}
