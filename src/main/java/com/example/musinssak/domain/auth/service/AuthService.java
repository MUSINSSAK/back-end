package com.example.musinssak.domain.auth.service;

import com.example.musinssak.api.auth.dto.LoginRequest;
import com.example.musinssak.api.auth.dto.LoginResponse;
import com.example.musinssak.api.auth.dto.RegisterRequest;
// ▼▼▼ DTO 임포트 추가 ▼▼▼
import com.example.musinssak.api.auth.dto.PasswordRequestDto;
import com.example.musinssak.api.auth.dto.PasswordResetDto;
import com.example.musinssak.api.auth.dto.PasswordVerifyDto;


public interface AuthService {

    // 기존 메소드
    void register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

    // ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼ 여기에 3개의 메소드 선언을 추가합니다 ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼

    /**
     * 비밀번호 재설정 요청을 처리하고 인증 이메일을 발송합니다.
     * @param requestDto 사용자가 입력한 이메일 정보
     */
    void requestPasswordReset(PasswordRequestDto requestDto);

    /**
     * 사용자가 입력한 인증번호를 검증합니다.
     * @param verifyDto 사용자의 이메일과 입력한 인증번호 정보
     */
    void verifyPasswordResetCode(PasswordVerifyDto verifyDto);

    /**
     * 인증이 완료된 사용자의 비밀번호를 재설정합니다.
     * @param resetDto 사용자의 이메일과 새로운 비밀번호 정보
     */
    void resetPassword(PasswordResetDto resetDto);

    // ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲ 여기까지 추가 ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
}