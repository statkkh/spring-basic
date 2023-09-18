package com.khkim.basic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.khkim.basic.dto.request.PostUserRequestDto;
import com.khkim.basic.dto.response.PostUserResponseDto;
import com.khkim.basic.entity.UserEntity;
import com.khkim.basic.repository.UserRepository;
import com.khkim.basic.service.MainService;

import lombok.RequiredArgsConstructor;
// 모든 결과물을 여기에서 
// description : ! @Component 해당 클래스를 자바 bean에 등록하여  Spring 이 인스턴스 생성을 알아서 하도록 하는 어노테이션
//  description : ! @Service @Component 와 동일한 작업을 수행하지만 가독성을 위해 Serivice 라는 이름을 가짐
@Service
@RequiredArgsConstructor
public class MainServiceImplement implements MainService {

    private final UserRepository userRepository;

    @Override
    public String getMethod() {
        return "This method is Get Method";
    }

    @Override
    public ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dto) {

        // INSERT INTO user(email, password, nickname,telNumber address , addressDetail);
        // VALUES(dto.getEmail() , dto.getPassword()...)

        // description : Create 작업 순서(INSERT) 
        // description : 1. Entity 인스턴스 생성 //
        UserEntity userEntity = new UserEntity(dto);
        //  description :2 .repository 의 save 메서드 사용 동일한 id가 있으면 존재하는 데이터에 수정, 없으면 생성//
        userRepository.save(userEntity); 

        return ResponseEntity.status(HttpStatus.OK).body(new PostUserResponseDto("SU","Success"));
    }
    
}
