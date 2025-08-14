package com.example.musinssak.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "EMAIL_DUPLICATED", "이미 사용 중인 이메일입니다."),
    NICKNAME_DUPLICATED(HttpStatus.BAD_REQUEST, "NICKNAME_DUPLICATED", "이미 사용 중인 닉네임입니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "요청 형식이 잘못되었습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}