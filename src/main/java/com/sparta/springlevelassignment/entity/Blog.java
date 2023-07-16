package com.sparta.springlevelassignment.entity;

import com.sparta.springlevelassignment.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // DB에 blog라는 이름으로 Table 생성
@Getter
@Setter
@NoArgsConstructor // 매개 값 없는 기본 생성자
@AllArgsConstructor //  매개변수 있는 생성자
@Table(name = "blog")
public class Blog extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    // 매핑하려는 객체가 다대일(N:1) 관계. 즉, 이 코드에서는 현재 클래스의 인스턴스가 다수의 User의 객체 중 하나에 매핑 될 수 있음
    // fecth는 속성을 Eager과 Lazy로 설정 가능. Lazy로 설정할 경우 객체를 실제로 필요한 순간에 가져오게 함
    @JoinColumn(name = "user_id",referencedColumnName = "id", insertable = true, updatable = true)
    // JoinColumn 어노테이션은 외래키(FK)를 지정하는데 사용.
    // 이 코드에서는 user_id 컬럼이 외래 키 역할을 하며 참조되는 user 클래스의 id 필드와 매핑 됨
    // referencedColumnName = 외래키가 참조하는 컬럼명, insertable,updatable = 해당 엔티티가 DB에 삽입,수정될 때 외래 키 열도 함께 변경 가능 여부를 나타냄
    private User user;

    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER)
    private List<Comment> commentList = new ArrayList<>();


    // Blog Entity의 생성자. 생성자란 객체를 생성할 때 객체의 초기화를 담당하는 메서드
    // Blog 객체를 생성할 때 사용, 객체를 생성하고 저장함으로써 새로운 게시글을 데이터 베이스에 등록할 수 있다.
    public Blog (BlogRequestDto blogRequestDto, User user) {
        this.title = blogRequestDto.getTitle();
        this.content = blogRequestDto.getContent();
        this.user = user;
        this.username = user.getUsername(); // blogRequestDto.getUsername();을 하면, blog 테이블의 username이 PostMan으로 보낸 이름으로 바뀜
    }

    // Post Entity의 필드 값을 해당 객체의 필드 값으로 변경, 즉 게시글을 수정하고 업데이트
    public void update(BlogRequestDto blogRequestDto) {
        this.title = blogRequestDto.getTitle();
        this.content = blogRequestDto.getContent();
    }
}
