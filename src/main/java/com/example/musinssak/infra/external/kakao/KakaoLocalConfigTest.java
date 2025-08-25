package com.example.musinssak.infra.external.kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoLocalConfigTest {

    private final KakaoLocalProperties kakaoLocalProperties;

    @PostConstruct
    public void checkConfig() {
        // Kakao Base URL: https://dapi.kakao.com
        log.info("Kakao Base URL: {}", kakaoLocalProperties.getBaseUrl());
        // Kakao REST API Key: Loaded
        log.info("Kakao REST API Key: {}", kakaoLocalProperties.getRestApiKey() != null ? "Loaded" : "Not Loaded");
    }
}
