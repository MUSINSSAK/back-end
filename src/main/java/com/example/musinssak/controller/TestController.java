package com.example.musinssak.controller;

import com.example.musinssak.dto.TestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test") // 경로가 명시돼야 Swagger에서 인식
public class TestController {

    @GetMapping
    public TestResponse hello() {
        return new TestResponse("hello from Swagger!");
    }
}
