package com.example.musinssak.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PasswordResetDto {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email; // 사용자의 이메일 주소

    // @Pattern: 이 필드의 값이 정해진 규칙(정규식)에 맞는지 검증합니다.
    // 여기서는 최소 8자, 최대 16자이면서 영문, 숫자, 특수문자를 포함해야 한다는 규칙을 적용했습니다.
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "비밀번호는 8~16자, 영문, 숫자, 특수문자를 포함해야 합니다.")
    @NotBlank(message = "새 비밀번호는 필수 입력 항목입니다.")
    private String newPassword; // 사용자가 설정할 새로운 비밀번호
}