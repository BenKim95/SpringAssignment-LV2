package com.sparta.springlevelassignment.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    // 로그인에서 필요한 정보 담는 DTO
    private String username;
    private String password;
}
