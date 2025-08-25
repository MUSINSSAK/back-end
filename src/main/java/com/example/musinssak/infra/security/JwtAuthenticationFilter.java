package com.example.musinssak.infra.security;

import com.example.musinssak.common.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT 인증 필터 (요청당 1회 실행)
 *
 * ✔ 핵심 흐름
 *  1) Swagger 문서 경로는 검사 스킵
 *  2) Authorization 헤더가 없으면 "익명"으로 통과 (보호 URL에서 401 판단)
 *  3) "Bearer <token>" 형식이면 토큰 검증
 *     - 유효하면 SecurityContext에 인증객체 세팅(로그인 상태)
 *     - 유효하지 않거나 만료/파싱오류면 요청에 AUTH_REQUIRED 표식만 남김
 *  4) 최종 401 JSON 응답은 SecurityConfig의 authenticationEntryPoint가 통일해서 내려줌
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // SecurityConfig.authenticationEntryPoint에서 읽어가는 표식 키
    private static final String ATTR_AUTH_ERROR = "AUTH_ERROR";

    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 0) Swagger/OpenAPI 문서 경로는 JWT 검사 스킵 (문서 로딩 깨지지 않도록)
        String uri = request.getRequestURI();
        if (uri.startsWith("/v3/api-docs") || uri.startsWith("/swagger-ui") || "/swagger-ui.html".equals(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 1) Authorization 헤더 확인
        String bearer = request.getHeader("Authorization");

        // 1-1) 헤더가 없거나 "Bearer " 형식이 아니면 → 익명으로 계속 진행
        //      (보호가 걸린 URL에 도달하면 Spring Security가 401을 판단하고 EntryPoint가 응답)
        if (!StringUtils.hasText(bearer) || !bearer.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2) "Bearer <token>"에서 <token>만 추출
        String token = bearer.substring(7);

        try {
            // 3) 토큰 유효성 검사 (서명/만료 등)
            if (tokenProvider.validateToken(token)) {

                // 3-1) 유효할 경우: 사용자 식별자(subject)와 권한(role) 추출
                String subject = tokenProvider.getSubject(token);

                String role = "USER"; // 기본 권한
                try {
                    var claims = tokenProvider.parseClaims(token);
                    Object r = claims.get("role");
                    if (r instanceof String s && StringUtils.hasText(s)) {
                        role = s;
                    }
                } catch (Exception ignored) {
                    // 클레임 파싱 실패 시 기본 USER 유지
                }

                // 3-2) 인증객체 생성 후 SecurityContext에 세팅 → 이후 컨트롤러에서 @AuthenticationPrincipal 사용 가능
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                var authentication = new UsernamePasswordAuthenticationToken(
                        subject,                 // principal(보통 userId)
                        null,                    // credentials(비번은 보관하지 않음)
                        authorities
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

//                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//                String username = auth.getName(); // 로그인한 유저 이름
//                log.info("[JWT] auth.getName()={}", username);

                // 유효한 토큰이면 끝. 다음 필터로 진행
                filterChain.doFilter(request, response);
                return;
            }

            // 3-3) 유효성 검사 결과가 false → 인증 실패 표식만 남기고 계속 진행
            SecurityContextHolder.clearContext();
            request.setAttribute(ATTR_AUTH_ERROR, ErrorCode.AUTH_REQUIRED);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // 3-4) 만료/파싱 오류 등 모든 예외 → 인증 실패 표식만 남김
            SecurityContextHolder.clearContext();
            request.setAttribute(ATTR_AUTH_ERROR, ErrorCode.AUTH_REQUIRED);
            filterChain.doFilter(request, response);
        }
    }

    /**
     * SecurityConfig.authenticationEntryPoint에서 사용:
     * 요청에 실어둔 인증 실패 표식(ErrorCode)을 꺼내온다.
     */
    public static ErrorCode extractAuthError(HttpServletRequest request) {
        Object v = request.getAttribute(ATTR_AUTH_ERROR);
        return (v instanceof ErrorCode) ? (ErrorCode) v : null;
    }
}
