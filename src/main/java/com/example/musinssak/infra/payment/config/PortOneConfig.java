// src/main/java/com/example/musinssak/infra/payment/config/PortOneConfig.java
package com.example.musinssak.infra.payment.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 포트원 결제 설정 클래스임
 * - 포트원 API 호출을 위한 WebClient를 설정함
 * - API 키와 스토어 ID 등 설정을 관리함
 */
@Configuration
@RequiredArgsConstructor
@Getter
public class PortOneConfig {

    @Value("${portone.api-secret}")
    private String apiSecret;

    @Value("${portone.store-id}")
    private String storeId;

    @Value("${portone.channel-key}")
    private String channelKey;

    /**
     * 포트원 API 호출용 WebClient를 생성함
     * - 기본 URL과 헤더를 설정함
     * - 인증 토큰을 자동으로 포함함
     */
    @Bean
    public WebClient portOneWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.portone.io")
                .defaultHeader("Authorization", "PortOne " + apiSecret)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    /**
     * 스토어 ID를 반환함
     * - 결제 요청 시 필요한 가맹점 식별자임
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * API Secret을 반환함
     * - 직접 API 호출 시 인증용
     */
    public String getApiSecret() {
        return apiSecret;
    }

    /**
     * 채널 키를 반환함
     * - V2 API 결제 요청용
     */
    public String getChannelKey() {
        return channelKey;
    }
}