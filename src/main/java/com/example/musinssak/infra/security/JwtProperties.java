package com.example.musinssak.infra.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 3단계: JwtProperties: yml 값을 자바 객체로 바인딩
 *
 * application.yml에서 jwt의
 * secret, access-expire-ms, refresh-expire-ms 값들을 자바 객체로 매핑해서,
 * 어디서든 props.getSecret()처럼 꺼내 쓰게 해주는 설정 바구니입니다
 * -> private final JwtProperties props; // 이런 식으로 주입 가능
 */
@Getter
@Component
@ConfigurationProperties(prefix = "jwt") // application.yml의 jwt.*를 바인딩
public class JwtProperties {
    private String secret;
    private long accessExpireMs;
    private long refreshExpireMs;

    // @ConfigurationProperties는 setter 필요
    public void setSecret(String secret) { this.secret = secret; }
    public void setAccessExpireMs(long accessExpireMs) { this.accessExpireMs = accessExpireMs; }
    public void setRefreshExpireMs(long refreshExpireMs) { this.refreshExpireMs = refreshExpireMs; }
}
