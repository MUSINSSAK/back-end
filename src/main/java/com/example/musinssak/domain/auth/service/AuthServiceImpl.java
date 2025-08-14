package com.example.musinssak.domain.auth.service;

import com.example.musinssak.api.auth.dto.RegisterRequest;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService; // user 도메인 서비스 주입
    private final PasswordEncoder passwordEncoder; // 인프라

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        // 1) 이메일/닉네임 중복 검사
        if (userService.emailExists(request.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_DUPLICATED);
        }
        if (userService.nicknameExists(request.getNickname())) {
            throw new BusinessException(ErrorCode.NICKNAME_DUPLICATED);
        }

        // 2) 패스워드는 해시로 저장 (BCrypt)
        String hash = passwordEncoder.encode(request.getPassword());

        // 3) 저장
        userService.createUser(request.getEmail(), hash, request.getNickname());
    }
}
