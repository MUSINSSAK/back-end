package com.example.musinssak.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TestResponse {

    @Schema(description = "테스트 응답 메시지")
    private String message;

    public TestResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
