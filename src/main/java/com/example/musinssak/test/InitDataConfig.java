// com.example.musinssak.test.InitDataConfig
package com.example.musinssak.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class InitDataConfig {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, ProductRepository productRepository) {
        return args -> {
            System.out.println("InitDataConfig 실행됨!");

            if (!userRepository.existsByEmail("sinbumjun@naver.com")) {
                userRepository.save(new User(
                        "sinbumjun@naver.com",
                        "1234",
                        "신범준",
                        LocalDateTime.now()
                ));
            }

            if (!productRepository.existsByName("러닝 바지")) {
                productRepository.save(new Product(
                        "러닝 바지",
                        "멋진 바지입니다",
                        39000,
                        30,
                        LocalDateTime.now()
                ));
            }
        };
    }
}
