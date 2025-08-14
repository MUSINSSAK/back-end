package com.example.musinssak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MusinssakApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusinssakApplication.class, args);
    }

}
