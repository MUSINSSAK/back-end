package com.example.musinssak.api.auth;

import com.example.musinssak.api.auth.dto.TokenResponse;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.infra.security.JwtTokenProvider;
import com.example.musinssak.infra.security.RefreshTokenStore;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RefreshController {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenStore refreshTokenStore;

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(HttpServletRequest req, HttpServletResponse res) {
        // 1) 쿠키에서 RT 읽기
        String cookieName = jwtTokenProvider.getRefreshCookieName(); // "REFRESH_TOKEN"
        String rt = readCookie(req, cookieName);
        if (rt == null || rt.isBlank()) {
            return unauthorized(res);
        }

        // 2) RT 서명/만료 검증
        if (!jwtTokenProvider.validateToken(rt)) {
            // [추가] 만료/위조 등으로 validate 실패 시, 가능한 경우 userId를 뽑아 Redis에서 해당 RT 삭제
            String uid = null;
            try {
                uid = jwtTokenProvider.getSubject(rt); // 유효하지 않으면 예외
            } catch (ExpiredJwtException eje) {
                uid = eje.getClaims().getSubject();   // 만료라면 subject는 추출 가능
            } catch (Exception ignored) { /* 위조/형식오류 등 → subject 추출 불가 */ }

            if (uid != null) {
                refreshTokenStore.delete(uid); // 서버 상태도 정리
            }
            return unauthorized(res);
        }

        // 3) subject = userId 추출
        String userId = jwtTokenProvider.getSubject(rt);

        // 4) Redis 대조 (재사용 방지)
        String stored = refreshTokenStore.get(userId);
        if (stored == null || !stored.equals(rt)) {
            // 저장된 값이 없거나 다르면 도난/재사용 가능 → 강제 로그아웃 효과
            refreshTokenStore.delete(userId);
            return unauthorized(res);
        }

        // 5) Access 발급
        String newAccess = jwtTokenProvider.createAccessToken(userId, "USER"); // 필요시 DB에서 role 조회

        // 6) RT 회전(새 RT 발급 → 저장 → 새 쿠키)
        String newRefresh = jwtTokenProvider.createRefreshToken(userId);
        refreshTokenStore.save(userId, newRefresh, jwtTokenProvider.getRefreshTtlSeconds());

        ResponseCookie cookie = ResponseCookie.from(cookieName, newRefresh)
                .httpOnly(true)
                .secure(false)   // 운영 HTTPS에서는 true
                .sameSite("Lax") // 크로스도메인이면 None + Secure
                .path("/")
                .maxAge(jwtTokenProvider.getRefreshTtlSeconds())
                .build();
        res.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(ApiResponse.success(
                "토큰이 재발급되었습니다.",
                new TokenResponse(newAccess)
        ));
    }

    private static String readCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) return c.getValue();
        }
        return null;
    }

    private ResponseEntity<ApiResponse<TokenResponse>> unauthorized(HttpServletResponse res) {
        ErrorCode error = ErrorCode.AUTH_REQUIRED;

        // RT 쿠키 즉시 만료
        ResponseCookie expired = ResponseCookie.from(jwtTokenProvider.getRefreshCookieName(), "")
                .httpOnly(true)
                .secure(false)     // 운영 HTTPS면 true
                .sameSite("Lax")   // 크로스도메인이면 None + Secure
                .path("/")
                .maxAge(0)
                .build();
        res.addHeader("Set-Cookie", expired.toString());

        ApiResponse<TokenResponse> body = new ApiResponse<>(
                error.getHttpStatus().value(),
                error.getCode(),
                null,
                error.getMessage()
        );
        return ResponseEntity.status(error.getHttpStatus()).body(body);
    }
}
