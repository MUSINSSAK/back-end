package com.example.musinssak.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 5단계: 로그인 응답 DTO
 */
@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
}