package com.example.musinssak.api.auth;

import com.example.musinssak.api.auth.dto.LoginRequest;
import com.example.musinssak.api.auth.dto.LoginResponse;
import com.example.musinssak.api.auth.dto.RegisterRequest;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.auth.service.AuthService;
import com.example.musinssak.infra.security.JwtTokenProvider;
import com.example.musinssak.infra.security.RefreshTokenStore;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; // 서비스는 다음 단계에서 구현
    private final JwtTokenProvider jwtTokenProvider;   // 토큰 생성 유틸
    private final RefreshTokenStore refreshTokenStore; // Redis에 RefreshToken 저장소
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
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse res) {
        log.info("로그인 요청: {}", request.getEmail());

        // LoginResponse tokens = authService.login(request);
        var body = authService.login(request); // userId, accessToken 채워서 반환
        String userId = body.getUserId();
        log.info("userId={}", userId);

        String refresh = jwtTokenProvider.createRefreshToken(userId);
        // String refresh = jwtTokenProvider.generateRefreshToken(userId); // 동일한 의미

        refreshTokenStore.save(userId, refresh, jwtTokenProvider.getRefreshTtlSeconds());

        ResponseCookie cookie = ResponseCookie.from(jwtTokenProvider.getRefreshCookieName(), refresh)
                .httpOnly(true).secure(false) // 운영 HTTPS는 true
                .sameSite("Lax")              // 크로스도메인 필요하면 None + Secure
                .path("/").maxAge(jwtTokenProvider.getRefreshTtlSeconds())
                .build();
        res.addHeader("Set-Cookie", cookie.toString());

        return ApiResponse.success("로그인에 성공했습니다.", body);
    }
}