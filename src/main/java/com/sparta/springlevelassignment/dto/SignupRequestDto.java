package com.sparta.springlevelassignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") // 이메일 형식에 허용되는 문자를 모두 사용
    @NotBlank
    private String email;

    private boolean admin = false;

    private String adminToken = "";

    public SignupRequestDto () {

    }
}
