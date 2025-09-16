package com.example.musinssak.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class TimeConfig {

    @Bean
    public Clock clock() {
        // 운영 타임존을 명확히 사용 (예: Asia/Seoul)
        return Clock.system(ZoneId.of("Asia/Seoul"));
        // 혹은 기본 시스템 타임존
        // return Clock.systemDefaultZone();
    }
}
