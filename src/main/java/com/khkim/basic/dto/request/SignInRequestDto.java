package com.khkim.basic.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// description 
// description 

@Setter
@Getter
@NoArgsConstructor
public class SignInRequestDto {
    
    // description
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
