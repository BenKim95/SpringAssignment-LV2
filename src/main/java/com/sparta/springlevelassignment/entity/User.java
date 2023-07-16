package com.sparta.springlevelassignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // 파라미터 값 비어있는 기본 생성자 생성
@AllArgsConstructor // 매개 값 있는 생성자
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true) // username 중복 방지
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true) // 이메일 중복 방지
    private String email;

    @Column(nullable = false) // USER -> USER
    @Enumerated(value = EnumType.STRING) // Enum타입을 Database컬럼에 DB를 저장할 때 사용
    private UserRoleEnum userRoleEnum;

    public User(String username, String password, String email, UserRoleEnum userRoleEnum) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRoleEnum = userRoleEnum;
    }
}
