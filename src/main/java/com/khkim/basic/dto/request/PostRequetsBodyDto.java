package com.khkim.basic.dto.request;

//  description :  DTO(Data Transfer Object) : 서로 다른 레이어 간에 데이터를 전송하고 자 할 때 사용하는 객체 //

//  description : lombok : 라이브러리 클래스 생성시 반복적으로 작성하는 메서드를 간편하게 작성하는 라이브러리 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostRequetsBodyDto {
    private String name;
    private Integer age;
}
