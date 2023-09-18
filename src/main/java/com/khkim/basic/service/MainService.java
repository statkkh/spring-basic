package com.khkim.basic.service;

import org.springframework.http.ResponseEntity;

import com.khkim.basic.dto.request.PostUserRequestDto;
import com.khkim.basic.dto.response.PostUserResonseDto;
// import com.khkim.basic.dto.response.ResponseDto;

public interface MainService {

    String getMethod();
    ResponseEntity<? super PostUserResonseDto> postUser(PostUserRequestDto dto);
    
}
