package com.example.musinssak.domain.user.service;

import com.example.musinssak.api.user.dto.UpdatePasswordRequest;
import com.example.musinssak.api.user.dto.UpdateUserProfileRequest;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean nicknameExists(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    @Transactional
    public User createUser(String email, String passwordHash, String nickname) {
        return userRepository.save(User.create(email, passwordHash, nickname));
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public User updateProfile(long userId, UpdateUserProfileRequest req) {
        // 1) 대상 유저 조회 (없으면 404)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2) 전달된 값이 있을 때만 갱신 (null이면 기존 값 유지)
        if (req.getName() != null) user.changeNickname(req.getName()); // name -> nickname
        if (req.getPhone() != null) user.changePhone(req.getPhone());
        if (req.getBirthDate() != null) user.changeBirthDate(req.getBirthDate());
        if (req.getProfileImageUrl() != null) user.changeProfileImageUrl(req.getProfileImageUrl());

        // 3) updated_at은 자동 갱신되도록 처리됨
        user.updateProfile(req.getName(), req.getPhone(), req.getBirthDate(), req.getProfileImageUrl());

        // 4) @Transactional로 관리되므로 반환만 하면됨 (save 불필요)
        return user;
    }

    @Transactional
    public String updatePassword(String userId, UpdatePasswordRequest request) {
        // 1) 사용자 조회
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 2) 현재 비밀번호가 일치하는지 확인
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new BusinessException(ErrorCode.INVALID_CURRENT_PASSWORD);  // 현재 비밀번호 불일치
        }

        // 3) 새 비밀번호 설정
        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        user.changePassword(encodedNewPassword);

        // 4) 비밀번호 변경일 갱신
        user.setLastPasswordModifiedAt();

        // 5) 저장
        userRepository.save(user);

        // 6) 마지막 변경일 반환 (yyyy-MM-dd 형식으로)
        return user.getLastPasswordModifiedAt().toLocalDate().toString();
    }

    @Override
    @Transactional(readOnly = true)
    public String getPasswordLastModified(String userId) {
        Long id = Long.parseLong(userId);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return user.getUpdatedAt()
                .toLocalDate()
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
