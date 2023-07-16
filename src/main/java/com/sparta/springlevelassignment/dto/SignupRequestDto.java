package com.sparta.springlevelassignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$") // 정규식: 소문자 알파벳과 숫자로 이루어져 있으며, 길이가 4에서 10 사이
    @NotBlank
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$") // 정규식 : 비밀번호가 소문자, 대문자, 숫자, 특수문자를 최소한 하나씩 포함하고 있으며, 총 길이가 8에서 15 사이
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
