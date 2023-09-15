package com.khkim.basic.service.implement;

import org.springframework.stereotype.Component;

import com.khkim.basic.service.MainService;
// 모든 결과물을 여기에서 

@Component
public class MainServiceImplement implements MainService {

    @Override
    public String getMethod() {
        return "This method is Get Method";
    }
    
}
