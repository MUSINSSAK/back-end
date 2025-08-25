package com.example.musinssak.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@ToString
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

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private User(String email, String passwordHash, String nickname) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.nickname = nickname;
    }

    public static User create(String email, String passwordHash, String nickname) {
        return new User(email, passwordHash, nickname);
    }

    // 닉네임(=이름) 변경
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    // 휴대폰 변경
    public void changePhone(String phone) {
        this.phone = phone;
    }

    // 생년월일 변경
    public void changeBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // 프로필 이미지 URL 변경
    public void changeProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    // updated_at 갱신
    public void updateProfile(String nickname, String phone, LocalDate birthDate, String profileImageUrl) {
        this.nickname = nickname;
        this.phone = phone;
        this.birthDate = birthDate;
        this.profileImageUrl = profileImageUrl;
        this.updatedAt = LocalDateTime.now(); // 현재 날짜로 갱신
    }

    // 비밀번호 변경 메서드
    public void changePassword(String encodedNewPassword) {
        this.passwordHash = encodedNewPassword; // 새로운 암호화된 비밀번호로 변경
    }

    // 비밀번호 변경일 갱신 메서드
    public void setLastPasswordModifiedAt() {
        this.lastPasswordModifiedAt = LocalDateTime.now(); // 현재 시간으로 변경일 갱신
    }
}
