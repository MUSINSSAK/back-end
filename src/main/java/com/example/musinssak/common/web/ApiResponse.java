package com.example.musinssak.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공통 API 응답 포맷
 * - status: HTTP 상태 코드(숫자)
 * - code: 비즈니스 코드("SUCCESS" 또는 에러 코드)
 * - data: 실제 응답 데이터(없으면 null)
 * - message: 선택(성공/에러 메시지)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String code;
    private T data;
    private String message; // 선택 필드 (null 가능)

    // ---------- 성공 ----------
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "SUCCESS", data, null);
    }
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, "SUCCESS", data, message);
    }
    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(200, "SUCCESS", null, message);
    }

    // ---------- 실패 ----------
    public static ApiResponse<Void> error(int status, String code, String message) {
        return new ApiResponse<>(status, code, null, message);
    }
}
