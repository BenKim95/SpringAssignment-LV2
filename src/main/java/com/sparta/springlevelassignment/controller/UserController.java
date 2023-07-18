package com.sparta.springlevelassignment.controller;

import com.sparta.springlevelassignment.dto.*;
import com.sparta.springlevelassignment.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // Responsebody + Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API
    @PostMapping ("/blog/signup")
    public ApiResult signup (@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        //Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new ApiResult("회원가입 실패", HttpStatus.NOT_FOUND); // 오류일 경우 404 Not Found
        }
        userService.signup(requestDto);

        return new ApiResult("회원가입 성공", HttpStatus.CREATED); // 성공할 경우 200 OK 반환
    }

    // 로그인 api
    @PostMapping("/blog/login")
    public ApiResult login (@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        userService.login(loginRequestDto, httpServletResponse);
        return new ApiResult("로그인 성공", HttpStatus.ACCEPTED);
    }

    //회원정보 조회 API
    @GetMapping("/blog/profile")
    public ProfileResponseDto showProfile(HttpServletRequest httpServletRequest) {
      return userService.showProfile(httpServletRequest);
    }

    //회원정보 수정 API
    @PutMapping("/blog/edit-profile")
    public ApiResult editProfile (@RequestBody ProfileEditRequestDto profileEditRequestDto, HttpServletRequest httpServletRequest) {
        return userService.editProfile(profileEditRequestDto, httpServletRequest);
    }
}
