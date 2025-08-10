package com.example.musinssak.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 API 경로 허용
                .allowedOrigins("http://localhost:5173") // 리액트 개발 서버
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용 메서드
                .allowCredentials(true); // 쿠키 허용
    }
}
