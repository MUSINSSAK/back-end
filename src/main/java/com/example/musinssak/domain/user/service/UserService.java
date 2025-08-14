package com.example.musinssak.domain.user.service;

import com.example.musinssak.domain.user.entity.User;

public interface UserService {
    boolean emailExists(String email);
    boolean nicknameExists(String nickname);
    User createUser(String email, String passwordHash, String nickname);
}
