package com.example.musinssak.domain.auth.service;

import com.example.musinssak.api.auth.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
}
