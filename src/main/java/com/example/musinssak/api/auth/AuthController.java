package com.example.musinssak.api.auth;

import com.example.musinssak.api.auth.dto.RegisterRequest;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}