package com.example.musinssak.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RefreshTokenStore {

    private final StringRedisTemplate redis;
    private static final String PREFIX = "RT:"; // 키: RT:<userId>

    /**
     * 저장: RT:<userId> = <refreshToken> (TTL 적용)
     */
    public void save(String userId, String refreshToken, long ttlSeconds) {
        redis.opsForValue().set(PREFIX + userId, refreshToken, Duration.ofSeconds(ttlSeconds));
    }

    /**
     * 조회
     */
    public String get(String userId) {
        return redis.opsForValue().get(PREFIX + userId);
    }

    /**
     * 일치 여부(회전/세션 보호용)
     */
    public boolean matches(String userId, String refreshToken) {
        String saved = get(userId);
        return saved != null && saved.equals(refreshToken);
    }

    /**
     * 삭제(로그아웃/강제 만료)
     */
    public void delete(String userId) {
        redis.delete(PREFIX + userId);
    }
}
