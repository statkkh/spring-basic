package com.khkim.basic.service.implement;

// import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.khkim.basic.dto.request.PatchNicknameRequestDto;
import com.khkim.basic.dto.request.PostUserRequestDto;
import com.khkim.basic.dto.request.SignInRequestDto;
import com.khkim.basic.dto.response.DeleteUserResponseDto;
import com.khkim.basic.dto.response.PatchNicknameResponseDto;
import com.khkim.basic.dto.response.PostUserResponseDto;
import com.khkim.basic.dto.response.ResponseDto;
import com.khkim.basic.dto.response.SignInResponseDto;

import com.khkim.basic.entity.UserEntity;
import com.khkim.basic.provider.JwtProvider;
import com.khkim.basic.repository.UserRepository;

import com.khkim.basic.service.MainService;


import lombok.RequiredArgsConstructor;
// 모든 결과물을 여기에서 
// description : ! @Component 해당 클래스를 자바 bean에 등록하여  Spring 이 인스턴스 생성을 알아서 하도록 하는 어노테이션
// description : ! @Service @Component 와 동일한 작업을 수행하지만 가독성을 위해 Serivice 라는 이름을 가짐
@Service
@RequiredArgsConstructor
public class MainServiceImplement implements MainService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    // description : 비밀번호를 안전하게 암호화하고 검증 하는 인터페이스 //
    // description : BCryptPasswordEncoder  - Bcrypt 해싱 알고리즘을 사용하는 PasswordEncoder 구현  클래스  //
    // description : component 에 등록 되어 있지 않음 //
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String getMethod() {
        return "This method is GET method.";
    }   

    @Override
    public ResponseEntity<? super PostUserResponseDto> postUser(PostUserRequestDto dto) {

        // INSERT INTO user(email, password, nickname,telNumber address , addressDetail);
        // VALUES(dto.getEmail() , dto.getPassword()...)
        try{
            // description : 비밀번호 암호화 작업  passwordEncoder //
            //  description : 1 . dto로부터 평문의 비밀번호(암호화할 문자열)을 가져오기
            // 2. passwordEncoder() 의 인스턴스의 encode()메서드로 암호화 한다. //
           
            String password = dto.getPassword();    
            String  encodedPassword = passwordEncoder.encode(password); 
            dto.setPassword(encodedPassword);
             
            // description : Create 작업 순서(INSERT) 
            // description : 1. Entity 인스턴스 생성 //
            UserEntity userEntity = new UserEntity(dto);
            //  description :2 .repository 의 save 메서드 사용 동일한 id가 있으면 존재하는 데이터에 수정, 없으면 생성//
            userRepository.save(userEntity); 
        }catch(Exception exception){
            exception.printStackTrace();///예외 처리 확인 방법
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "Database Error"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new PostUserResponseDto("SU","Success"));
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto) {
        
        // UPDATE user SET nickname = dto.getNickname() WHERE email = dto.getEmail();

        try {

            // description: Update 작업 순서 (UPDATE) //
            // description: 1. Entity 인스턴스 조회 //
            // description: findById() - primary key를 사용하여 레코드를 검색하는 메서드 //
            // SELECT * FROM user WHERE email = ??;
            UserEntity userEntity = userRepository.findById(dto.getEmail()).get();

            // description: 2. Entity 인스턴스 수정 //
            userEntity.updateNickname(dto.getNickname());

            // description: 3. repository의 save 메서드 사용 //
            // description: save() - Entity 객체를 테이블에 저장하는 메서드 //
            // description: 만약 해당 인스턴스의 ID 값과 동일한 레코드가 존재하면 해당 레코드를 수정 //
            // description: 그렇지 않다면 레코드를 생성 //
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "Database Error"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new PatchNicknameResponseDto("SU", "SUCCESS"));

    
    }

    @Override
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(String email) {
        try {
        // description: Delete 작업 순서 (DELETE) //
        // description: 1. repository의 deleteById 메서드 사용 //
            userRepository.deleteById(email);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "Database Error"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new DeleteUserResponseDto("SU", "SUCCESS"));
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        SignInResponseDto responseBody = null;


        try {
            String email = dto.getEmail();
        
            //description  1. dto로 받은 email을 이용 데이터베이스에서 검색 //
            UserEntity userEntity = userRepository.findByEmail(email);
            //description  2. email 에 해당하는 레코드가 존재의 여부 확인dto로 받은 email을 이용 데이터베이스에서 검색 //
            if(userEntity == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDto("SF", "Sign in error"));
            // description 3. userEntity 암호화 되어 있는 password 추출 //
            String encodedPassword = userEntity.getPassword();
            //  description :dto 평문의 password추출 //
            String password = dto.getPassword();
            //  description : 5. 암호화되어 있는 password와평문의 password비교  //
            //  description :matches() 평문의 문자열과 암호화된 문자열과 비교
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);

            if(!isMatched)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDto("SF", "Sign in Failed"));

            // description 토큰 -생성 //
            String token = jwtProvider.create(email);

            responseBody = new SignInResponseDto("SU", "SUCCESS", token);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "Database Error"));    
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

 
}
