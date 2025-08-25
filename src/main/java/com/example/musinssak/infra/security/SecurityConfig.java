package com.example.musinssak.infra.security;

import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.common.web.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // ← 메서드별 매칭을 위해 추가
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 인증·인가(Security) 관련 설정
 * 6-3)
 * - “모두”는 permitAll
 * - “회원”은 authenticated
 * - 첫 매칭 우선이므로, 더 구체적인/메서드 제한 규칙을 위에 배치
 */
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // BCrypt 해시 (회원가입/로그인에서 사용)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 개발 단계: 인증 잠금 해제(permitAll). JWT 필터 붙인 뒤 보호 범위 조정 예정.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})  // CORS 활성화
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 프리플라이트

                        /* ---------- ① 모두(permitAll) ---------- */
                        // 회원가입/로그인/비번찾기 3단계
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login",
                                "/api/auth/password/request",
                                "/api/auth/password/verify",
                                "/api/auth/password/reset"
                        ).permitAll()

                        // 상품 공개 API (GET만 공개로 한정)
                        .requestMatchers(HttpMethod.GET, "/api/products/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/main").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/best").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()            // 카테고리 목록
                        .requestMatchers(HttpMethod.GET, "/api/products/*").permitAll()          // 상품 상세

                        .requestMatchers(HttpMethod.GET, "/api/products/*/reviews").permitAll()  // 리뷰 목록
                        .requestMatchers(HttpMethod.GET, "/api/products/*/questions").permitAll()// 문의 목록(조회만 공개)
                        .requestMatchers(HttpMethod.GET, "/api/questions/types").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/address/search").permitAll()

                        // Swagger / OpenAPI
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        /* ---------- ② 회원(authenticated) 작업할때만 ---------- */
                        // 토큰 재발급 (Refresh Token → 새 Access Token 발급)
                        .requestMatchers(HttpMethod.POST, "/api/auth/refresh").permitAll()

                        // 로그아웃
                        .requestMatchers(HttpMethod.POST, "/api/auth/logout").permitAll()

                        // 내 정보 / 배송지 / 계정 보안
                        .requestMatchers("/api/users/me/**").permitAll()

                        // 상품 문의 등록(POST만 보호)
                        .requestMatchers(HttpMethod.POST, "/api/products/*/questions").permitAll()

                        // 찜/최근 본 상품
                        .requestMatchers("/api/users/me/wishlist/**").permitAll()
                        .requestMatchers("/api/users/me/recent-products/**").permitAll()

                        // 장바구니
                        .requestMatchers("/api/cart/**").permitAll()

                        // 주문
                        .requestMatchers("/api/orders/**").permitAll()

                        /* ---------- ③ 그 외 ---------- */
                        .anyRequest().permitAll() // 개발 단계: 나머지는 공개. 필요 시 authenticated()로 전환
                )

                .exceptionHandling(ex -> ex
                        // 인증 실패(토큰 없음/무효/만료 등) → 항상 401 + AUTH_REQUIRED
                        .authenticationEntryPoint((req, res, e) -> {
                            var code = JwtAuthenticationFilter.extractAuthError(req);
                            if (code == null) code = ErrorCode.AUTH_REQUIRED;  // 헤더 없음 등 기본값

                            res.setStatus(code.getHttpStatus().value());       // 401
                            res.setContentType("application/json;charset=UTF-8");
                            // 캐시 방지 + UTF-8 보장
                            res.setHeader("Cache-Control", "no-store");
                            res.setCharacterEncoding("UTF-8");

                            var body = ApiResponse.error(
                                    code.getHttpStatus().value(),   // 401
                                    code.getCode(),                 // "AUTH_REQUIRED"
                                    code.getMessage()               // "로그인이 필요합니다."
                            );

                            new ObjectMapper().writeValue(res.getWriter(), body);
                        })
                        // 추후 관리자 페이지 구현시 : 인가 실패(ROLE 부족 등) → 403
                        .accessDeniedHandler((req, res, e) -> {
                            var code = ErrorCode.AUTH_FORBIDDEN; // 한 곳(enum)에서 상태/코드/메시지 관리
                            res.setStatus(code.getHttpStatus().value());   // 403
                            res.setContentType("application/json;charset=UTF-8");
                            res.setHeader("Cache-Control", "no-store");
                            res.setCharacterEncoding("UTF-8");

                            var body = ApiResponse.error(
                                    code.getHttpStatus().value(),   // 403
                                    code.getCode(),                 // "AUTH_FORBIDDEN"
                                    code.getMessage()               // "접근 권한이 없습니다."
                            );

                            new ObjectMapper().writeValue(res.getWriter(), body);
                        })
                )

                // JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 배치
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
