package com.example.musinssak.infra.external.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component  // 이 클래스가 스프링 빈으로 등록되게 함
@ConfigurationProperties(prefix = "kakao.local")  // yml에서 kakao.local로 시작하는 값을 읽어옵니다.
public class KakaoLocalProperties {
    private String baseUrl;      // 주소검색 API 기본 URL
    private String restApiKey;   // 카카오 REST API 키
}
