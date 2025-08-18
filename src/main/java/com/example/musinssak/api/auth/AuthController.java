package com.example.musinssak.api.auth;

import com.example.musinssak.api.auth.dto.LoginRequest;
import com.example.musinssak.api.auth.dto.LoginResponse;
import com.example.musinssak.api.auth.dto.RegisterRequest;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; // 서비스는 다음 단계에서 구현

    /**
     * 회원가입
     * POST /api/auth/register
     * @param request 회원가입 요청 데이터 (이메일, 비밀번호, 닉네임)
     * @return 회원가입 성공 응답
     */
    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ApiResponse.success("회원가입이 완료되었습니다.");
    }

    /**
     * 로그인
     * POST /api/auth/login
     * @param request 로그인 요청 데이터 (이메일, 비밀번호)
     * @return 로그인 성공 응답
     */
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("로그인 요청: {}", request);
        LoginResponse tokens = authService.login(request);
        return ApiResponse.success("로그인에 성공했습니다.", tokens);
    }
}