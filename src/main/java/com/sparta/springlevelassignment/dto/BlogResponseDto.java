package com.sparta.springlevelassignment.dto;

import com.sparta.springlevelassignment.entity.Blog;
import com.sparta.springlevelassignment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class BlogResponseDto { // 게시글 조회 요청에 대한 응답으로 쓰이는 DTO
    private Long blog_id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> blogcommentList;

    public BlogResponseDto (Blog blog) {
        this.blog_id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.username = blog.getUsername();
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
        this.blogcommentList = blog.getCommentList()
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
        // stream은 요소들의 연속적인 흐름을 나타냄
        // map() 메서드를 통하여 스트림의 요소들을 CommentResponseDto 객체로 변환, CommentResponseDto::new는 CommentResponseDto 클래스의 생성자를 참조하는 메서드 레퍼런스
        // Collectors.toList()를 이용하여 스트림의 요소들을 리스트로 수집, 이를 통해 CommentResponseDto 객체로 변환된 댓글들이 리스트로 저장
    }

    public BlogResponseDto(Blog blog, List<Comment> commentList) { // 여기가 프로필 수정할때 반환되는 ResponseDTO
        this.blog_id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.username = blog.getUsername();
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
        this.blogcommentList = commentList.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
