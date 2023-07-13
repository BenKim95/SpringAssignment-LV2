package com.sparta.springlevelassignment.service;

import com.sparta.springlevelassignment.jwt.JwtUtil;
import com.sparta.springlevelassignment.repository.BlogRepository;
import com.sparta.springlevelassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class BlogService {

    private final BlogRepository blogRepository; // DB와 연결
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

}
