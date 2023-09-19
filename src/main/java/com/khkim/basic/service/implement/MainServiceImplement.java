package com.khkim.basic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.khkim.basic.dto.request.PatchNicknameRequestDto;
import com.khkim.basic.dto.request.PostUserRequestDto;
import com.khkim.basic.dto.response.PostUserResponseDto;
import com.khkim.basic.dto.response.ResponseDto;
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
        return "This method is GET method.";
    }   

    @Override
    public ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dto) {

        // INSERT INTO user(email, password, nickname,telNumber address , addressDetail);
        // VALUES(dto.getEmail() , dto.getPassword()...)
        try{
            // description : Create 작업 순서(INSERT) 
            // description : 1. Entity 인스턴스 생성 //
            UserEntity userEntity = new UserEntity(dto);
            //  description :2 .repository 의 save 메서드 사용 동일한 id가 있으면 존재하는 데이터에 수정, 없으면 생성//
            userRepository.save(userEntity); 
        }catch(Exception exception){
            exception.printStackTrace();///예외 처리 확인 방법
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "DATABASE ERROR"));
        }
        

        return ResponseEntity.status(HttpStatus.OK).body(new PostUserResponseDto("SU","Success"));
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto) {
        try{
        //UPDATE user SET nickname = dto.getNickname  WHERE email = dto.getEmail();
            //description : Update 작업 순서: (UPADATE)
            //description : 1. Entity 인스턴스 조회
            //description : findById 메서드 -0 primary key 사용하여 레코드를 검색하는 메서드 
            // select * from user where email = ???;
            UserEntity userEntity = userRepository.findById(dto.getEmail()).get();
            //description : 2. Entity 인스턴스 수정
            userEntity.updateNickname(dto.getNickname());
            //description : 3. Entity 인스턴스 save 메서드 사용  // 
            //description : save() - Entity 객체를 테이블에 저장하는 메서드 //
            //description : 만약 해당 인스턴스의 ID값과 동일한 레코드가 존재하면 해당 레코드 수정 //
            //description : 그렇지 않다면 레코드를 생성 // 
            userRepository.save(userEntity);            
        }catch(Exception exception){
            exception.printStackTrace();///예외 처리 확인 방법
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "DATABASE ERROR "));
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PatchNicknameRequestDto("", ""));
    }

 
    @Override
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(String email) {
        
        // DELETE FROM user WHERE email = email ;
            //description : Delete 작업 순서  // 
            //description : 1. repository 의 deleteByid 메서드 사용 //

            userRepository.deleteById(email);                    
        
        try {
            
        } catch (Exception exception) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "Database Error"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new DeleteUserResponseDto("SU", "SUCCESS"));

    }
    
}
