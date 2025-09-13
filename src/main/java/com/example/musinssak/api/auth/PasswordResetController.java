package com.example.musinssak.api.auth;

import com.example.musinssak.api.auth.dto.PasswordRequestDto;
import com.example.musinssak.api.auth.dto.PasswordResetDto;
import com.example.musinssak.api.auth.dto.PasswordVerifyDto;
import com.example.musinssak.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// @RestController: 이 클래스가 RESTful API의 컨트롤러임을 나타냅니다.
// 이 어노테이션이 붙은 클래스의 메소드들은 기본적으로 JSON 형태의 데이터를 반환합니다.
@RestController
// @RequestMapping("/api/auth/password"): 이 컨트롤러의 모든 메소드에 대한 공통 URL 경로를 설정합니다.
// 예를 들어, requestReset 메소드는 /api/auth/password/request 경로로 매핑됩니다.
@RequestMapping("/api/auth/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final AuthService authService;

    // [1단계] 비밀번호 재설정 요청 (인증번호 발송)
    // @PostMapping("/request"): HTTP POST 요청을 /api/auth/password/request 경로로 매핑합니다.
    @PostMapping("/request")
    // ResponseEntity<?>: HTTP 응답의 상태 코드, 헤더, 본문을 모두 포함할 수 있는 클래스입니다.
    // @Valid: DTO에 설정한 유효성 검사(@NotBlank, @Email 등)를 수행하도록 합니다.
    // @RequestBody: HTTP 요청의 본문(body)에 담긴 JSON 데이터를 DTO 객체로 변환해줍니다.
    public ResponseEntity<?> requestReset(@Valid @RequestBody PasswordRequestDto requestDto) {
        // AuthService에 있는 로직을 호출하여 이메일을 보냅니다.
        authService.requestPasswordReset(requestDto);

        // API 명세서에 따라 성공 응답을 구성하여 반환합니다.
        // ResponseEntity.ok(): HTTP 상태 코드 200 (OK)를 의미합니다.
        // body(): 응답 본문에 들어갈 데이터를 설정합니다. Map.of를 사용해 간단한 JSON 객체를 만듭니다.
        return ResponseEntity.ok().body(Map.of(
                "status", 200,
                "code", "SUCCESS",
                "message", "인증번호가 이메일로 전송되었습니다."
        ));
    }

    // [2단계] 인증번호 확인
    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@Valid @RequestBody PasswordVerifyDto verifyDto) {
        // AuthService를 통해 인증번호를 검증합니다.
        authService.verifyPasswordResetCode(verifyDto);

        // 성공 시, 명세서에 맞는 응답을 반환합니다.
        return ResponseEntity.ok().body(Map.of(
                "status", 200,
                "code", "SUCCESS",
                "message", "인증번호 확인이 완료되었습니다."
        ));
    }

    // [3단계] 비밀번호 재설정
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordResetDto resetDto) {
        // AuthService를 통해 비밀번호를 재설정합니다.
        authService.resetPassword(resetDto);

        // 성공 시, 명세서에 맞는 응답을 반환합니다.
        return ResponseEntity.ok().body(Map.of(
                "status", 200,
                "code", "SUCCESS",
                "message", "비밀번호가 성공적으로 재설정되었습니다."
        ));
    }
}