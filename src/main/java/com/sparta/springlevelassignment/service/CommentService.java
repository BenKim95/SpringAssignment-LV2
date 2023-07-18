package com.sparta.springlevelassignment.service;

import com.sparta.springlevelassignment.dto.ApiResult;
import com.sparta.springlevelassignment.dto.CommentRequestDto;
import com.sparta.springlevelassignment.dto.CommentResponseDto;
import com.sparta.springlevelassignment.entity.Blog;
import com.sparta.springlevelassignment.entity.Comment;
import com.sparta.springlevelassignment.entity.User;
import com.sparta.springlevelassignment.entity.UserRoleEnum;
import com.sparta.springlevelassignment.jwt.JwtUtil;
import com.sparta.springlevelassignment.repository.BlogRepository;
import com.sparta.springlevelassignment.repository.CommentRepository;
import com.sparta.springlevelassignment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 댓글 작성
    @Transactional
    public CommentResponseDto createComment(Long blogId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        User user = checkToken(httpServletRequest);
        log.info(user.getPassword());

        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(user, commentRequestDto, blog);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        User user = checkToken(httpServletRequest);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
        );

        if (comment.getUser().getUsername().equals(user.getUsername()) || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        } else {
            throw new IllegalArgumentException("작성자 또는 관리자만 수정할 수 있습니다.");
        }
    }

    // 댓글 삭제
    @Transactional
    public ApiResult deleteComment(Long commentId, HttpServletRequest httpServletRequest) {
        User user = checkToken(httpServletRequest);

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
        );

        if (comment.getUser().getUsername().equals(user.getUsername()) || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {
            commentRepository.delete(comment);
            return new ApiResult("댓글 삭제 성공", HttpStatus.ACCEPTED);
        } else {
            return new ApiResult("작성자 또는 관리자만 댓글을 삭제할 수 있습니다.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // Token 체크
    public User checkToken(HttpServletRequest request){

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            return user;

        }
        return null;
    }
}
