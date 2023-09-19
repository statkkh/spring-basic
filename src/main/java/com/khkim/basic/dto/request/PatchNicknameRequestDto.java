package com.khkim.basic.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class PatchNicknameRequestDto {
    private String email;
    private String nickname;
}
