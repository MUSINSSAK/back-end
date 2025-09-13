package com.example.musinssak.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

// @Getter: 이 클래스의 모든 필드에 대한 getter 메소드를 자동으로 만들어줍니다.
// 예를 들어, getEmail() 메소드를 직접 작성하지 않아도 됩니다.
@Getter
public class PasswordRequestDto {

    // @NotBlank: 이 필드의 값이 비어있거나 공백만으로 이루어질 수 없다는 것을 검증합니다.
    // "email": "" 또는 "email": " " 같은 값은 허용되지 않습니다.
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    // @Email: 이 필드의 값이 유효한 이메일 형식인지 검증합니다.
    // "test@example.com" 같은 형식이어야 합니다.
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email; // 프론트엔드에서 보낸 이메일 주소를 저장할 변수
}