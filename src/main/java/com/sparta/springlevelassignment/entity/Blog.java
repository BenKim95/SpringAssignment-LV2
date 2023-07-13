package com.sparta.springlevelassignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // DB에 blog라는 이름으로 Table 생성
@Getter
@NoArgsConstructor // 매개 값 없는 기본 생성자
@AllArgsConstructor //  매개변수 있는 생성자
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK (해당 값은 user의 id(PK) 값과 동일
    private User user;
}
