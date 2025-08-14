package com.example.musinssak.common.exception;

import com.example.musinssak.common.web.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusiness(BusinessException ex) {
        var ec = ex.getErrorCode();
        return ResponseEntity.status(ec.getHttpStatus())
                .body(ApiResponse.error(ec.getHttpStatus().value(), ec.getCode(), ec.getMessage()));
    }

    // (선택) 바디 검증 실패 등 형식 오류
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiResponse<Void>> handleInvalid(Exception ex) {
        var ec = ErrorCode.INVALID_REQUEST;
        return ResponseEntity.status(ec.getHttpStatus())
                .body(ApiResponse.error(ec.getHttpStatus().value(), ec.getCode(), ec.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknown(Exception ex) {
        var ec = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(ec.getHttpStatus())
                .body(ApiResponse.error(ec.getHttpStatus().value(), ec.getCode(), ec.getMessage()));
    }
}