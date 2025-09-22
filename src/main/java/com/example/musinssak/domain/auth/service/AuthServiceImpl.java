package com.example.musinssak.domain.auth.service;

import com.example.musinssak.api.auth.dto.*;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.repository.UserRepository;
import com.example.musinssak.domain.user.service.UserService;
import com.example.musinssak.infra.email.EmailService;
import com.example.musinssak.infra.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor // final이 붙은 필드들을 사용하는 생성자를 자동으로 만들어줍니다 (의존성 주입).
public class AuthServiceImpl implements AuthService {

    // 필요한 부품들(객체)을 선언합니다.
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화 도구
    private final JwtTokenProvider jwtTokenProvider; // JWT 토큰 생성 도구
    private final EmailService emailService; // 이메일 발송 도구

    // 인증번호를 임시로 저장할 공간 (Key: 이메일, Value: 인증번호)
    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();
    // 이메일 인증을 완료했는지 여부를 임시로 저장할 공간
    private final Map<String, Boolean> verifiedEmails = new ConcurrentHashMap<>();

    @Override // AuthService 인터페이스에 정의된 register 기능을 실제로 구현합니다.
    @Transactional // 이 작업이 하나의 묶음(트랜잭션)으로 처리되도록 합니다. 중간에 실패하면 원래대로 돌아갑니다.
    public void register(RegisterRequest request) {
        // 1. 이메일이 이미 존재하는지 확인합니다.
        if (userService.emailExists(request.getEmail())) {
            // 존재한다면, 미리 정의해둔 '이메일 중복' 에러를 발생시킵니다.
            throw new BusinessException(ErrorCode.EMAIL_DUPLICATED);
        }
        // 2. 비밀번호를 암호화합니다.
        String hash = passwordEncoder.encode(request.getPassword());
        // 3. 암호화된 비밀번호로 새로운 유저를 생성합니다.
        userService.createUser(request.getEmail(), hash, request.getNickname());
    }

    @Override // login 기능을 실제로 구현합니다.
    @Transactional(readOnly = true) // 데이터를 변경하지 않고 읽기만 하는 작업임을 알려 성능을 최적화합니다.
    public LoginResponse login(LoginRequest request) {
        // 1. 이메일로 사용자를 찾습니다. 없으면 '이메일 없음' 에러를 발생시킵니다.
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_NOT_FOUND));

        // 2. 입력된 비밀번호와 DB에 저장된 암호화된 비밀번호가 일치하는지 확인합니다.
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            // 일치하지 않으면 '비밀번호 틀림' 에러를 발생시킵니다.
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }

        // 3. 로그인이 성공하면, 사용자의 정보를 담아 Access Token을 생성합니다.
        String userId = String.valueOf(user.getId());
        String accessToken = jwtTokenProvider.generateAccessToken(userId, Map.of(
                "email", user.getEmail(),
                "nickname", user.getNickname(),
                "role", "USER"
        ));
        // 4. 생성된 토큰 정보를 응답으로 돌려줍니다.
        return new LoginResponse(userId, accessToken);
    }

    @Override // 비밀번호 찾기 1단계: 인증번호 요청 기능을 구현합니다.
    @Transactional
    public void requestPasswordReset(PasswordRequestDto requestDto) {
        String email = requestDto.getEmail();

        // 1. 이메일로 가입된 사용자가 있는지 확인합니다. 없으면 에러를 발생시킵니다.
        userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_NOT_FOUND));

        // 2. 6자리 랜덤 인증번호를 만듭니다.
        String verificationCode = generateVerificationCode();
        // 3. 만든 인증번호를 임시 저장소에 이메일과 함께 저장합니다.
        verificationCodes.put(email, verificationCode);

        // 4. 이메일 제목과 내용을 설정합니다.
        String subject = "[MUSINSSAK] 비밀번호 찾기 인증번호 안내";
        String text = "인증번호: " + verificationCode + "\n\n이 인증번호는 10분간 유효합니다.";
        // 5. 설정된 내용으로 이메일을 발송합니다.
        emailService.sendEmail(email, subject, text);

        // 6. 10분 뒤에 임시 저장소에서 인증번호가 자동으로 삭제되도록 설정합니다.
        scheduleCodeRemoval(email, 10, TimeUnit.MINUTES);
    }

    @Override // 비밀번호 찾기 2단계: 인증번호 확인 기능을 구현합니다.
    public void verifyPasswordResetCode(PasswordVerifyDto verifyDto) {
        String email = verifyDto.getEmail();
        String code = verifyDto.getCode();

        // 1. 임시 저장소에서 해당 이메일로 저장된 인증번호를 가져옵니다.
        String storedCode = verificationCodes.get(email);

        // 2. 저장된 코드가 없으면 (시간이 만료되었거나 잘못된 요청) '코드 만료' 에러를 발생시킵니다.
        if (storedCode == null) {
            throw new BusinessException(ErrorCode.CODE_EXPIRED);
        }
        // 3. 입력한 코드와 저장된 코드가 다르면 '코드 불일치' 에러를 발생시킵니다.
        if (!storedCode.equals(code)) {
            throw new BusinessException(ErrorCode.INVALID_CODE);
        }

        // 4. 인증에 성공했으므로, 사용했던 인증번호는 삭제합니다.
        verificationCodes.remove(email);
        // 5. 대신 '이메일 인증 완료' 상태를 임시 저장소에 기록합니다.
        verifiedEmails.put(email, true);
        // 6. '인증 완료' 상태도 10분 뒤에 자동으로 삭제되도록 설정합니다.
        scheduleVerifiedEmailRemoval(email, 10, TimeUnit.MINUTES);
    }

    @Override // 비밀번호 찾기 3단계: 비밀번호 재설정 기능을 구현합니다.
    @Transactional
    public void resetPassword(PasswordResetDto resetDto) {
        String email = resetDto.getEmail();

        // 1. 해당 이메일이 '인증 완료' 상태인지 확인합니다.
        if (!verifiedEmails.getOrDefault(email, false)) {
            // 아니라면, '미인증 이메일' 에러를 발생시킵니다.
            throw new BusinessException(ErrorCode.UNVERIFIED_EMAIL);
        }

        // 2. 이메일로 사용자를 다시 찾습니다.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_NOT_FOUND));

        // 3. User 엔티티에 있는 'changePassword' 메소드를 호출하여 새 비밀번호(암호화된)로 변경합니다.
        user.changePassword(passwordEncoder.encode(resetDto.getNewPassword()));

        // 4. 비밀번호 변경이 완료되었으므로, '인증 완료' 상태를 삭제합니다.
        verifiedEmails.remove(email);
    }

    // --- 내부에서만 사용하는 보조 메소드들 ---

    // 6자리 숫자 인증번호를 생성하는 메소드
    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(900000) + 100000; // 100000 ~ 999999 사이의 숫자
        return String.valueOf(num);
    }

    // 지정된 시간 후에 인증번호를 삭제하는 스케줄링 메소드
    private void scheduleCodeRemoval(String email, long delay, TimeUnit unit) {
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            verificationCodes.remove(email);
        }, delay, unit);
    }

    // 지정된 시간 후에 '인증 완료' 상태를 삭제하는 스케줄링 메소드
    private void scheduleVerifiedEmailRemoval(String email, long delay, TimeUnit unit) {
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            verifiedEmails.remove(email);
        }, delay, unit);
    }
}