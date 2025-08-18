package com.example.musinssak.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * 4단계: JwtTokenProvider (토큰 생성 유틸)
 *
 * @PostConstruct init()에서 .env→application.yml로 들어온 secret을 HMAC 키로 변환해 한 번만 준비.
 * 액세스 토큰: subject(일반적으로 userId) + 필요한 클레임(email/nickname/role 등) 포함
 * 리프레시 토큰: subject만(또는 최소) 포함, 수명은 더 길게 -> 보관/폐기는 나중에 Redis/DB에서 관리.
 */
@Component
public class JwtTokenProvider {

    private final JwtProperties props; // yml/.env 값 바인딩된 설정
    private Key key;                   // HS256 서명용 키

    public JwtTokenProvider(JwtProperties props) {
        this.props = props;
    }

    @PostConstruct
    void init() {
        // HS256용 HMAC 키 준비
        // secret은 충분히 길어야 함(최소 32바이트/256비트 이상 권장)
        this.key = Keys.hmacShaKeyFor(props.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 액세스 토큰 생성
     * @param subject 보통 userId(불변 식별자) 문자열
     * @param claims  추가로 넣을 클레임 (email/nickname/role 등)
     */
    public String generateAccessToken(String subject, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + props.getAccessExpireMs()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 리프레시 토큰 생성 (클레임 최소화 권장)
     */
    public String generateRefreshToken(String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + props.getRefreshExpireMs()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 6단계: JWT 인증 필터(보호 API 접근)
     */
    // 6-1) 토큰을 파싱해 Claims(내용물)를 꺼낸다. 서명/만료 검증도 함께 진행됨
    public Claims parseClaims(String token) {
        // secret key로 서명 검증 + 만료 시간 체크
        // 검증 실패 시 JwtException 발생
        return Jwts.parserBuilder()
                .setSigningKey(key)             // 서명 검증 키
                .build()
                .parseClaimsJws(token)          // 토큰 파싱, 실패 시 JwtException
                .getBody();                     // 성공 시 Claims 반환
    }

    // 토큰이 유효(서명 OK, 만료 전)하면 true 반환
    public boolean validateToken(String token) {
        try {
            parseClaims(token);                // 파싱 성공 → 유효
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 서명 오류, 만료, 형식 오류 등 → 유효하지 않음
            return false;
        }
    }

    // 토큰의 subject(보통 userId)만 추출
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

}
