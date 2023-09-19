package com.khkim.basic.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

public class PatchNicknameResponseDto extends ResponseDto {
    
    public PatchNicknameResponseDto (String code, String nickname){
        super(code, nickname);
    }
}
