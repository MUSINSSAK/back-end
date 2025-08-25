package com.example.musinssak.api.user.dto;

import com.example.musinssak.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserProfileResponse {

    private String name;
    private String email;
    private String phone;
    private String birthDate;
    private String profileImageUrl;

    public UserProfileResponse(User user) {
        this.name = user.getNickname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.birthDate = user.getBirthDate() != null ? user.getBirthDate().toString() : null;
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
