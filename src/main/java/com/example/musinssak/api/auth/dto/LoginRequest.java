package com.example.musinssak.api.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 5단계: 로그인 요청 DTO
 */
@Getter
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}