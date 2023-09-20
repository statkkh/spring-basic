package com.khkim.basic.service;

import org.springframework.http.ResponseEntity;

import com.khkim.basic.dto.request.PatchNicknameRequestDto;
import com.khkim.basic.dto.request.PostUserRequestDto;
<<<<<<< HEAD
import com.khkim.basic.dto.response.DeleteUserResponseDto;
import com.khkim.basic.dto.response.PatchNicknameResponseDto;
import com.khkim.basic.dto.response.PostUserResponseDto;
// import com.khkim.basic.dto.response.ResponseDto;
// DeleteUserResponseDto
=======
import com.khkim.basic.dto.response.PatchNicknameResponseDto;
import com.khkim.basic.dto.response.PostUserResponseDto;
// import com.khkim.basic.dto.response.ResponseDto;

>>>>>>> 96b1917e1bb11d2d04b1f080e3cc44070022257d
public interface MainService {

    String getMethod();
    ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dto);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto);
<<<<<<< HEAD
    ResponseEntity<? super DeleteUserResponseDto> deleteUser(String email);
=======
>>>>>>> 96b1917e1bb11d2d04b1f080e3cc44070022257d
    
}
