package com.example.musinssak.domain.user.service;

import com.example.musinssak.api.user.dto.UpdatePasswordRequest;
import com.example.musinssak.api.user.dto.UpdateUserProfileRequest;
import com.example.musinssak.domain.user.entity.User;

import java.util.Optional;

public interface UserService {
    boolean emailExists(String email);
    boolean nicknameExists(String nickname);
    User createUser(String email, String passwordHash, String nickname);

    User getUserById(long userId);

    User updateProfile(long parseLong, UpdateUserProfileRequest request);

    String updatePassword(String userId, UpdatePasswordRequest request);
}
