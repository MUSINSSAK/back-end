package com.example.musinssak.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String email;

    // DB 컬럼명은 password → 의미 명확히 하려고 필드명은 passwordHash
    @Column(name = "password", nullable = false, length = 255)
    private String passwordHash;

    // 정책상 필수면 nullable=false, 선택이면 true로 바꾸세요
    @Column(nullable = false, length = 100)
    private String nickname;

    @Column(length = 50)
    private String phone;

    private LocalDate birthDate;

    @Column(length = 255, name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "last_password_modified_at")
    private LocalDateTime lastPasswordModifiedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;     // DB에서 기본값 주는 중이면 그대로 매핑만

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private User(String email, String passwordHash, String nickname) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.nickname = nickname;
    }

    public static User create(String email, String passwordHash, String nickname) {
        return new User(email, passwordHash, nickname);
    }
}
