package com.sparta.springlevelassignment.controller;

import com.sparta.springlevelassignment.dto.ApiResult;
import com.sparta.springlevelassignment.dto.BlogRequestDto;
import com.sparta.springlevelassignment.dto.BlogResponseDto;
import com.sparta.springlevelassignment.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class BlogController {

    private final BlogService blogService;

    //Blog 작성 API
    @PostMapping("/api/blog")
    public BlogResponseDto createBlog (@RequestBody BlogRequestDto blogRequestDto, HttpServletRequest httpServletRequest) {
        return blogService.createblog(blogRequestDto, httpServletRequest);
    }

    // Blog 전체 조회 API
    @GetMapping("/api/blog")
    public List<BlogResponseDto> getBlogs () {
        return blogService.getBlogs();
    }

    // 선택 Blog 조회 API
    @GetMapping("/api/blog/{id}")
    public BlogResponseDto getBlog (@PathVariable Long id) {
        return blogService.getBlog(id);
    }
    // Blog 수정 API
    @PutMapping("/api/blog/{id}")
    public BlogResponseDto updateBlog (@PathVariable Long id, @RequestBody BlogRequestDto blogRequestDto, HttpServletRequest httpServletRequest) {
        return blogService.updateBlog(id, blogRequestDto, httpServletRequest);
    }

    // Blog 삭제 API
    @DeleteMapping("/api/blog/{id}")
    public ApiResult deleteBlog (@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return blogService.deleteBlog(id, httpServletRequest);
    }
}
