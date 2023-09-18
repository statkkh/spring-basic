package com.khkim.basic.dto.response;

import lombok.Getter;

@Getter
public class PostUserResonseDto  extends ResponseDto{

    public PostUserResonseDto(String code, String message) {
        super(code, message);
    }
    
}
