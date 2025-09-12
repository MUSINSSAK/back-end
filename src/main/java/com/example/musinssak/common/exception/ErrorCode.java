package com.example.musinssak.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 상세 페이지
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT_NOT_FOUND", "해당 상품을 찾을 수 없습니다."),

    // 비밀번호 변경 관련 (400)
    INVALID_CURRENT_PASSWORD(HttpStatus.BAD_REQUEST, "INVALID_CURRENT_PASSWORD", "현재 비밀번호가 일치하지 않습니다."),

    // 인증(401) : 프론트는 이 코드만 보면 로그인 유도
    AUTH_REQUIRED(HttpStatus.UNAUTHORIZED, "AUTH_REQUIRED", "로그인이 필요합니다."),

    // 인가(403) : 로그인은 했으나 권한 부족
    AUTH_FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH_FORBIDDEN", "접근 권한이 없습니다."),

    // 회원가입 실패 관련
    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "EMAIL_DUPLICATED", "이미 사용 중인 이메일입니다."),
    NICKNAME_DUPLICATED(HttpStatus.BAD_REQUEST, "NICKNAME_DUPLICATED", "이미 사용 중인 닉네임입니다."),

    // 로그인 실패 관련
    EMAIL_NOT_FOUND(HttpStatus.UNAUTHORIZED, "EMAIL_NOT_FOUND", "등록되지 않은 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "INVALID_PASSWORD", "비밀번호가 일치하지 않습니다."),

    // 사용자 프로필 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자 정보를 찾을 수 없습니다."),

    // 사용자 배송지 관련
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "해당 배송지에 대한 권한이 없습니다."),
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "ADDRESS_NOT_FOUND", "배송지를 찾을 수 없습니다."),
    ADDRESS_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "ADDRESS_LIMIT_EXCEEDED", "배송지는 최대 5개까지 등록할 수 있습니다."),

    // 주소검색
    EMPTY_QUERY(HttpStatus.BAD_REQUEST, "EMPTY_QUERY", "검색어를 입력해주세요."),
    NO_SEARCH_RESULT(HttpStatus.NOT_FOUND, "NO_SEARCH_RESULT", "검색 결과가 없습니다."),
    EXTERNAL_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "EXTERNAL_API_ERROR", "주소 검색 서비스에 일시적인 오류가 발생했습니다."),


    // ===== 장바구니 / 상품옵션 추가 (Cart) =====
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "CART_NOT_FOUND", "장바구니를 찾을 수 없습니다."),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "CART_ITEM_NOT_FOUND", "장바구니 상품을 찾을 수 없습니다."),
    PRODUCT_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT_OPTION_NOT_FOUND", "상품 옵션을 찾을 수 없습니다."),
    OUT_OF_STOCK(HttpStatus.CONFLICT, "OUT_OF_STOCK", "재고가 부족합니다."),
    CART_ITEM_QUANTITY_INVALID(HttpStatus.BAD_REQUEST, "CART_ITEM_QUANTITY_INVALID", "수량은 1 이상이어야 합니다."),

    // 오더
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "로그인이 필요합니다"),
    NO_SELECTED_ITEMS(HttpStatus.BAD_REQUEST, "NO_SELECTED_ITEMS", "주문할 상품을 선택해주세요."),
    STOCK_RESERVATION_FAILED(HttpStatus.CONFLICT, "STOCK_RESERVATION_FAILED", "동시에 주문이 몰려 재고 예약에 실패했습니다. 잠시 후 다시 시도해주세요."),
    INSUFFICIENT_STOCK(HttpStatus.CONFLICT, "INSUFFICIENT_STOCK", "재고가 부족합니다."),

//주문창입력
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER_NOT_FOUND", "주문을 찾을 수 없습니다."),
    ORDER_TIME_EXPIRED(HttpStatus.BAD_REQUEST, "ORDER_TIME_EXPIRED", "주문 시간이 만료되었습니다."),

    // 공통
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