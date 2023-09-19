package com.khkim.basic.service;

import org.springframework.http.ResponseEntity;

import com.khkim.basic.dto.request.PatchNicknameRequestDto;
import com.khkim.basic.dto.request.PostUserRequestDto;
import com.khkim.basic.dto.response.DeleteUserResponseDto;
import com.khkim.basic.dto.response.PatchNicknameResponseDto;
import com.khkim.basic.dto.response.PostUserResponseDto;
// import com.khkim.basic.dto.response.ResponseDto;
// DeleteUserResponseDto
public interface MainService {

    String getMethod();
    ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dto);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto);
    ResponseEntity<? super DeleteUserResponseDto> deleteUser(String email);
    
}
