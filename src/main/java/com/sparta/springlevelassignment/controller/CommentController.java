package com.sparta.springlevelassignment.controller;

import com.sparta.springlevelassignment.dto.ApiResult;
import com.sparta.springlevelassignment.dto.CommentResponseDto;
import com.sparta.springlevelassignment.dto.CommentRequestDto;
import com.sparta.springlevelassignment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성 API
    @PostMapping("/comment/{blogId}") // 블로그 id 값으로 댓글 입력
    public CommentResponseDto createComment(@PathVariable Long blogId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        return commentService.createComment(blogId, commentRequestDto, httpServletRequest);
    }

    // 댓글 수정 API
    @PutMapping("/comment/{commentId}") // comment id 값으로 댓글 수정
    public CommentResponseDto updateComment (@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        return commentService.updateComment (commentId, commentRequestDto, httpServletRequest);
    }

    // 댓글 삭제 API
    @DeleteMapping("/comment/{commentId}") // comment id 값으로 댓글 삭제
    public ApiResult deleteComment (@PathVariable Long commentId, HttpServletRequest httpServletRequest) {
        return commentService.deleteComment(commentId, httpServletRequest);
    }
}
