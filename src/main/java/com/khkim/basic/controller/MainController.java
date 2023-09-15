package com.khkim.basic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khkim.basic.dto.request.PatchValidationDto;
import com.khkim.basic.dto.request.PostRequestBodyDto;
import com.khkim.basic.dto.response.TmpReponseDTO;
import com.khkim.basic.service.MainService;
import com.khkim.basic.service.implement.MainServiceImplement;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



// description: Controller    레이어드 아키텍처 상의 프레젠테이션 영역   //
// description: 클라이언트로부터 요청(입력)을 받고 서비스 결과를 응답(출력) 하는 영역//
// description:  @RestController : REST API 형식 Controller 를 만드고자 할 때 사용하는 어노테이션
// description: Response Body의 타입이 JSON 형태의 데이터를 반환
@RestController  
// description: @RequestMapping - Request  의  URL의 패턴에 따라 클래스 및 메서드
@RequestMapping("/api/v1/module1") // "http://localhost:4000/**    **뒤에 여러개 가능 "
// @RequestMapping("/main") // "http://localhost:4000/main/**
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    

    //http://localhost:4000/hello GET 
    @RequestMapping(value = "hello", method = {RequestMethod.POST})
    public String hello(){
        return "Hello spring framwork";
    }
    // description :  @RequestMapping 중 GET METHOD 에  한정하여 인식
    // description : 데이터를 얻기 위한 요청
    // description : 데이터 입력시 URL로 입력
    // description : 
    @GetMapping("")
    public String getMethod() {
        return mainService.getMethod();
    }
    
    // description :  @RequestMapping 중 POST METHOD 에  한정하여 인식
    // description : 데이터를 생성하기 위한 요청
    // description : 데이터 입력시  Body로 입력(get, delete)
    @PostMapping("")
    public String postMethod(){
        return "This is Post Method";
    }

    // description :  @RequestMapping  중  PUT Method 에 한정하여 인식//
    // description :  데이터를 수정하기 위하여 요청(전체 수정)    
    // description : 데이터 입력시  Body로 입력(post,patch, put ) requestbody에  데이터 포함//
    @PutMapping("")
    public String putMethod(){
        return "This is Put Method";
    }

    // description : @RequestMapping 중  Patch Method 에 한정하여 인식 
    // description : 데이터를 수정하기 위한 작업 (일부 수정)
    // description : 데이터 입력시  Body로 입력
    @PatchMapping("")
    public String PatchMethod(){
        return "This is Patch Method";
    }

    // description : @RequestMapping 중  Delete Method 에 한정하여 인식 //
    // description : 데이터를 삭제하기 위한 작업 //
    // description : 데이터 입력시  URL 입력
    @DeleteMapping("")
    public String DeleteMethod(){
        return "This is Delete Method";
    }

    // description :@Path Varible - Path 자체를 변수의 값으로 인식  //
    // description : {변수명}로 표현 -> @PathVariable("변수명")로 받음//
    @GetMapping("path-variable/{variable}")
    public String getPathVarible(
        @PathVariable("variable") String variable
        ){
              return "Parameter value : " + variable;  
        
    }

    // description  : @RequestParam - Query Parameter로 key 와  value 를 받아옴    //
    // description : Query Parameter - ?name1 = value&name2=value...    //  
    // description : @RequestParam("name1") -> name1 에대한  value1 를 받음     //
    // description 데이터 받기 //
    @GetMapping("parameter")
    public String getParameter(
        @RequestParam("name") String name,
        @RequestParam("age") Integer age
    ){
        return "name: " + name + ", age : " + age;   

    }   
    // description : @RequestBody - Request  Body에 포함된  데이터를 받아옴
    //  description : 문자열혹은 객체로 받을 수 있음 
    
    @PostMapping("request-body")
    public String postRequestBody(
        // @RequestBody String requestBody text 형태
        // 객체로 입력 받아서  작업
        @RequestBody PostRequestBodyDto requestBody
    ){
        return "Request 의 Body는 " + requestBody.getName() + " " + requestBody.getAge() + " 입니다" ;
    }

    @PatchMapping("validation")
    public String validation(
        // description : DTO 에 작성된 유효성 검사를 적용하려 한다면 @Valud 를 매개변수 자리에 추가해야 함//
        @RequestBody @Valid PatchValidationDto requestBody
    ){
        return requestBody.getArg1() ;
    }
 
    // @GetMapping("response-entity")
    // public ResponseEntity<TmpReponseDTO> gResponseEntity(){
    //     TmpReponseDTO responseBody = new TmpReponseDTO(getMethod(), null);
    // }
 
    // @GetMapping("/api/v1/module1/{taskNumber}")    
    // public Integer getMethod(@RequestParam Integer taskNumber) {
        
    //     System.out.println("taskNumber: " + taskNumber);

    //     return taskNumber ;
    // }
    
    // @PostMapping("/api/v1/module1")    
    // public Integer postMethod(@RequestParam Integer taskNumber) {
        
    //     System.out.println("taskNumber: " + taskNumber);

    //     return taskNumber ;
    // }

    // @PatchMapping("/api/v1/module1/{taskNumber}")    
    // public Integer PatchMethod(@RequestParam Integer taskNumber) {
        
    //     System.out.println("taskNumber: " + taskNumber);

    //     return taskNumber ;
    // }    
    // @DeleteMapping("/api/v1/module1/{taskNumber}")    
    // public Integer DeleteMethod(@RequestParam Integer taskNumber) {
        
    //     System.out.println("taskNumber: " + taskNumber);

    //     return taskNumber ;
    // } 

}
