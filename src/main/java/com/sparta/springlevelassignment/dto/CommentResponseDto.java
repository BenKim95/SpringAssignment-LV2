package com.sparta.springlevelassignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.springlevelassignment.entity.Blog;
import com.sparta.springlevelassignment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long comment_id;
    private String comment;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto (Comment comment) {
        this.comment_id = comment.getId();
        this.comment = comment.getComment();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
