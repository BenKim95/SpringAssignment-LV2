package com.sparta.springlevelassignment.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto { //Blog Entity를 생성할 때 필요 정보를 담음
    private String title;
    private String content;
}
