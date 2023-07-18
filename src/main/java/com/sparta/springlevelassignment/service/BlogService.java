package com.sparta.springlevelassignment.service;

import com.sparta.springlevelassignment.dto.ApiResult;
import com.sparta.springlevelassignment.dto.BlogRequestDto;
import com.sparta.springlevelassignment.dto.BlogResponseDto;
import com.sparta.springlevelassignment.entity.Blog;
import com.sparta.springlevelassignment.entity.User;
import com.sparta.springlevelassignment.entity.UserRoleEnum;
import com.sparta.springlevelassignment.jwt.JwtUtil;
import com.sparta.springlevelassignment.repository.BlogRepository;
import com.sparta.springlevelassignment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class BlogService {

    private final BlogRepository blogRepository; // DB와 연결
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    // Blog 작성 매서드
    @Transactional
    public BlogResponseDto createblog(BlogRequestDto blogRequestDto, HttpServletRequest httpServletRequest) {

        //토큰 체크
        User user = checkToken(httpServletRequest);

        log.info(user.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("인증되지 않은 사용자입니다.");
        }

        Blog blog = new Blog(blogRequestDto, user);
        blogRepository.save(blog);
        return new BlogResponseDto(blog);
    }

    // 전체 Blog 조회 매서드
    @Transactional
    public List<BlogResponseDto> getBlogs() {
        List<Blog> blogs = blogRepository.findAllByOrderByCreatedAtDesc();
        List<BlogResponseDto> blogResponseDto = new ArrayList<>();

        for (Blog blog : blogs) {
            blogResponseDto.add(new BlogResponseDto(blog));
        }

        return blogResponseDto;
    }

    // 단건 Blog 조회 매서드
    @Transactional(readOnly = true)
    public BlogResponseDto getBlog(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("블로그 아이디가 일치하지 않습니다."));
        return new BlogResponseDto(blog);
    }

    // Blog 수정 매서드
    @Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto, HttpServletRequest httpServletRequest) {
        User user = checkToken(httpServletRequest);

        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 블로그는 존재하지 않습니다.")
        );

        if (user == null) {
            throw new IllegalArgumentException("블로그 작성자만 수정이 가능합니다.");
        }

        // 블로그 작성자 or 관리자만 글 수정 가능 권한 설정
        if (blog.getUser().equals(user) || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {
            blog.update(blogRequestDto);
        } else {
            throw new IllegalArgumentException("블로그 작성자 또는 관리자만 수정이 가능합니다.");
        }

        return new BlogResponseDto(blog);
    }

    //Blog 삭제
    @Transactional
    public ApiResult deleteBlog(Long id, HttpServletRequest httpServletRequest) {
        // 토큰 체크
        User user = checkToken(httpServletRequest);

        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 글이 존재하지 않습니다.")
        );

        if (user == null) {
            throw new IllegalArgumentException("작성자만 글을 삭제할 수 있습니다.") ;
        }

        if (blog.getUser().equals(user) || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)){
            blogRepository.delete(blog);
        } else {
            throw new IllegalArgumentException("글 작성자 또는 관리자만 삭제할 수 있습니다.");
        }

        return new ApiResult("블로그 삭제를 성공했습니다.", HttpStatus.ACCEPTED);
    }

    // Token 체크
    public User checkToken(HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                return null;
            }
            // 토큰에서 가져온 사용자 정보를 조회하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            return user;
        }
        return null;
    }


}
