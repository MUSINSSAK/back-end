package com.example.musinssak.domain.auth.service;

import com.example.musinssak.api.auth.dto.LoginRequest;
import com.example.musinssak.api.auth.dto.LoginResponse;
import com.example.musinssak.api.auth.dto.RegisterRequest;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.repository.UserRepository;
import com.example.musinssak.domain.user.service.UserService;
import com.example.musinssak.infra.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService; // user 도메인 서비스 주입
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 인프라
    private final JwtTokenProvider jwtTokenProvider; // 토큰 생성 유틸

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        // 1) 이메일/닉네임 중복 검사
        if (userService.emailExists(request.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_DUPLICATED);
        }
//        if (userService.nicknameExists(request.getNickname())) {
//            throw new BusinessException(ErrorCode.NICKNAME_DUPLICATED);
//        }

        // 2) 패스워드는 해시로 저장 (BCrypt)
        String hash = passwordEncoder.encode(request.getPassword());

        // 3) 저장
        userService.createUser(request.getEmail(), hash, request.getNickname());
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_NOT_FOUND)); // 이메일 유효성 검사

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD); // 비밀번호 유효성 검사
        }

        String userId = String.valueOf(user.getId()); // 토큰 subject는 불변 식별자 권장
        // Access 토큰만 생성
        String accessToken = jwtTokenProvider.generateAccessToken(userId, Map.of(
                "email", user.getEmail(),
                "nickname", user.getNickname(),
                "role", "USER"
        ));

        // 리프레시 토큰 생성 (클레임 최소화 권장)
        // String refreshToken = jwtTokenProvider.generateRefreshToken(userId);

        // 응답 data에 액세스 토큰과 리프레시 토큰 전달
        // return new LoginResponse(accessToken, refreshToken);

        // ❌ 여기서 refreshToken 만들지 않음 (컨트롤러에서 쿠키로 내려줄 것)
        return new LoginResponse(userId, accessToken); // DTO는 userId + accessToken만
    }
}
