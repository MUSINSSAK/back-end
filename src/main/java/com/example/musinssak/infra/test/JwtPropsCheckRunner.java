package com.example.musinssak.infra.test;

import com.example.musinssak.infra.security.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class JwtPropsCheckRunner implements CommandLineRunner {
    private final JwtProperties props;

    @Override public void run(String... args) {
        System.out.printf(
                "JWT OK: secret.len=%d, access=%dms, refresh=%dms, issuer=%s, cookie=%s%n",
                props.getSecret() == null ? 0 : props.getSecret().length(),
                props.getAccessExpireMs(),
                props.getRefreshExpireMs(),
                props.getIssuer(),
                props.getRefreshCookieName()
        );
    }
}