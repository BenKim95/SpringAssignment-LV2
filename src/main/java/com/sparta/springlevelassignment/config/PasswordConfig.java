package com.sparta.springlevelassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean // Bean 수동 등록  annotation
    public PasswordEncoder passwordEncoder () { // PasswordEncoder 구현체 중에서 BCryptPasswordEncoder를 선택
        return new BCryptPasswordEncoder(); // SprngSecurity에서 제공하는 클래스로 비밀번호 암호화를 해주는 매서드
    }
}
