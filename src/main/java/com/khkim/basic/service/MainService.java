package com.khkim.basic.service;

import org.springframework.http.ResponseEntity;

import com.khkim.basic.dto.request.PatchNicknameRequestDto;
import com.khkim.basic.dto.request.PostUserRequestDto;
import com.khkim.basic.dto.response.PatchNicknameResponseDto;
import com.khkim.basic.dto.response.PostUserResponseDto;
// import com.khkim.basic.dto.response.ResponseDto;

public interface MainService {

    String getMethod();
    ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dto);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto);
    
}
