package com.sparta.springlevelassignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileEditRequestDto {
    private String password;
    private String changepassword;
    private String introduction;

}
