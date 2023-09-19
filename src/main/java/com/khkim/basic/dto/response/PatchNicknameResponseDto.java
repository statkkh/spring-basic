package com.khkim.basic.dto.response;

// import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;

@Getter
@NoArgsConstructor

public class PatchNicknameResponseDto extends ResponseDto{
    
    public PatchNicknameResponseDto ( String code, String message){
        super(code, message);
    }
}
