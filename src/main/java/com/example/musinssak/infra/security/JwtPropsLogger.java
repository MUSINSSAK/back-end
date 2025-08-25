package com.example.musinssak.infra.security;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtPropsLogger {
    private final JwtProperties props;

    @PostConstruct
    void logProps() {
        // [JWT] accessExpireMs=900000, refreshExpireMs=1209600000
        log.info("[JWT] accessExpireMs={}, refreshExpireMs={}",
                props.getAccessExpireMs(), props.getRefreshExpireMs());
        // secret은 보안상 로그로 출력하지 마세요
    }
}
