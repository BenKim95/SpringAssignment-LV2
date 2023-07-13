package com.sparta.springlevelassignment.service;

import com.sparta.springlevelassignment.dto.ApiResult;
import com.sparta.springlevelassignment.dto.LoginRequestDto;
import com.sparta.springlevelassignment.dto.SignupRequestDto;
import com.sparta.springlevelassignment.entity.User;
import com.sparta.springlevelassignment.entity.UserRoleEnum;
import com.sparta.springlevelassignment.jwt.JwtUtil;
import com.sparta.springlevelassignment.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

//        public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    // 회원가입
    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 있습니다. Statuscode: " + HttpStatus.BAD_REQUEST);
        }

        // email 중복 확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 주소입니다. Statuscode: " + HttpStatus.BAD_REQUEST);
        }

        //사용자 Role 확인
        UserRoleEnum roleEnum = UserRoleEnum.User;
        if (requestDto.isAdmin()) { // isAdmin은 admin의 boolean 값을 가져온다. Getter & Setter와 같은 매서드 규칙이다.
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("Admin 비밀번호가 올바르지 않아 등록이 불가능합니다. Statuscode: " + HttpStatus.NOT_FOUND);
            }
            roleEnum = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        log.info("user 객체 생성");
        User user = new User(username, password, email, roleEnum);
        log.info("Repository save 매서드");
        userRepository.save(user);
        log.info("save 성공");
    }

    // 로그인
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        //사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다. Statuscode: " + HttpStatus.BAD_REQUEST)
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) { // 첫번째 파라미터는 encoding 안된 비밀번호, 두번째는 encoding된 난수 비밀번호
            throw new IllegalArgumentException("잘못된 비밀번호입니다. Statuscode: " + HttpStatus.BAD_REQUEST);
        }

        //JWT 토큰 생성 및 반환 ※ 2번째 파라미터 잘 되는지 확인 필요
        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getUserRoleEnum()));
    }
}
