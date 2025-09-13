package com.example.musinssak.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PasswordVerifyDto {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email; // 사용자의 이메일 주소

    // @Size: 이 필드의 길이가 6이어야 한다고 검증합니다. 인증번호가 6자리이기 때문입니다.
    @Size(min = 6, max = 6, message = "인증번호는 6자리여야 합니다.")
    @NotBlank(message = "인증번호는 필수 입력 항목입니다.")
    private String code; // 사용자가 입력한 6자리 인증번호
}
