package com.khkim.basic.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtProvider {

    private String secretKey = "S3cr3tk3y";

    
    // description : Jwt 생성 메서드 //
    public String create(String subject ){

        // description :  토큰 만료시간(현재시간으로부터 1시간 후)  //
        Date expiration = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        // description :  JWT 생성 //
        // description :  JWT  클래스의 builder()메서드를 통해서 작성을 시작//
        String jwt = Jwts.builder()

        // description : 2. signwith() 메서드를 통해서 서명 알고리즘 서명에 사용할 비밀키 지정 //
                        .signWith(SignatureAlgorithm.HS256, secretKey )
        // description : 3. setmeothod 통한 payload 작성 //               
        // description : setSubject() 생성 주체(접근 주체) ,setIssuedAt() - 생성 시간  setExpiration : 만료시간(토큰생성시간, 토큰 만료시간//
                        .setSubject(subject).setIssuedAt(new Date()).setExpiration(expiration)
        // description : 4. compact 통한 jwt 생성종료 //               
                        .compact();

        return jwt;                        
    }

    //description : jwt 검증 메서드 //
    //description :  검증 결과 과정 //
    // description :    1.jwt 받아옴 //
    // description :    2. 받아온 우리가 알고 있는 secret key로 검증 처리//
    // description :    3. 검증 완료후 jwt에서 payload를 꺼내옴 //
    // description :   4. payload에서 원하는 데이터(subject)를 반환 //
    public String validate(String jwt){
        
        Claims claims = null;
        try {
            // description :  Jwts 클래스의 parser()클래스를 통해서 파싱시작 //
            claims = Jwts.parser()
            // description : setSignKey() 메서드를 통해서 parser를 통해서 비밀키 등록하여 검증 //
                                .setSigningKey(secretKey)            
            // description : parseClaimsJws() 메서드를 통해서 파싱 처리 //
                                .parseClaimsJws(jwt)                
            // description :  getBody()메서드를 통해서 플레임(페이도르)를 꺼내옴    //                    
                                .getBody();            
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }


        if(claims == null) return null;    
        // description :   ()를 통해서 원하는 정보 가져옴 //
        String subject = claims.getSubject();               
        
        return subject;
                            



    }


}
