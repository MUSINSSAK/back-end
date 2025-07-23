package com.example.musinssak.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User(String email, String password, String name, LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
    }

    // 비밀번호 변경 로직 (비즈니스 규칙 추가)
    public void changePassword(String newPassword) {
        if (newPassword.length() < 4) {
            throw new IllegalArgumentException("비밀번호는 최소 4자 이상이어야 합니다.");
        }
        this.password = newPassword;
    }
}
