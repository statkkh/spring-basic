package com.khkim.basic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private String code;
    private String message;
}

//1 .클래스 
//2 .빈인스턴스 NoArgsConstructor