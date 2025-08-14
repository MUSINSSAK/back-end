package com.example.musinssak.api.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String nickname;
}