package com.example.musinssak.common.config;

// WebClientConfig.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient aiServerWebClient() {
        return WebClient.builder()
                .baseUrl("http://ai-server:8000") // 도커 서비스 이름 사용
                .build();
    }
}