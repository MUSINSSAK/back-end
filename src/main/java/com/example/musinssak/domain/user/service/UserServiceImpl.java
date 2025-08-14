package com.example.musinssak.domain.user.service;

import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
}
