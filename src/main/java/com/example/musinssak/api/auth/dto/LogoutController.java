package com.example.musinssak.api.auth.dto;

import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.infra.security.JwtTokenProvider;
import com.example.musinssak.infra.security.RefreshTokenStore;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenStore refreshTokenStore;

    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletRequest req, HttpServletResponse res) {
        String userId = null;

        // 1) Authorization 헤더에서 AT 시도
        String bearer = req.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            String at = bearer.substring(7);
            userId = jwtTokenProvider.tryGetSubjectEvenIfExpired(at);
        }

        // 2) RT 쿠키로도 시도(AT가 없거나 만료된 경우 대비)
        if (userId == null) {
            String rt = readCookie(req, jwtTokenProvider.getRefreshCookieName());
            if (rt != null && !rt.isBlank()) {
                userId = jwtTokenProvider.tryGetSubjectEvenIfExpired(rt);
            }
        }

        // 3) Redis에서 RT 제거 (idempotent)
        if (userId != null) {
            refreshTokenStore.delete(userId);
        }

        // 4) RT 쿠키 즉시 만료
        ResponseCookie expired = ResponseCookie.from(jwtTokenProvider.getRefreshCookieName(), "")
                .httpOnly(true)
                .secure(false)     // 운영 HTTPS면 true
                .sameSite("Lax")   // 크로스도메인이면 None + Secure
                .path("/")
                .maxAge(0)
                .build();
        res.addHeader("Set-Cookie", expired.toString());

        return ApiResponse.success("로그아웃되었습니다.");
    }

    private static String readCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie c : cookies) if (name.equals(c.getName())) return c.getValue();
        return null;
    }
}
