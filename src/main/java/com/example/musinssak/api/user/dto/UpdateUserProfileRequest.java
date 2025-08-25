package com.example.musinssak.api.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 프로필 수정 요청 DTO
 * - null로 오는 필드는 "변경하지 않음"
 */
@Getter
@NoArgsConstructor
public class UpdateUserProfileRequest {
    private String name;              // 이름 (엔티티의 nickname과 매핑)
    private String phone;             // 휴대폰 번호 (하이픈 포함된 문자열)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;      // 생년월일 (yyyy-MM-dd)

    private String profileImageUrl;   // 프로필 이미지 URL (선택)
}
