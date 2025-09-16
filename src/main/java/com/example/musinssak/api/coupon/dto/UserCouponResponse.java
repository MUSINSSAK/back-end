package com.example.musinssak.api.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자 쿠폰 응답 DTO (단일 쿠폰)
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCouponResponse {
    private Long couponId;
    private String couponName;
    private String couponDescription;
    private String couponType;         // "PERCENT" | "AMOUNT" | "FREE_DELIVERY"
    private Integer discountRate;      // 퍼센트 할인율 (nullable)
    private Integer discountAmount;    // 정액 할인 금액 (nullable)
    private Integer minOrderPrice;
    private Integer maxDiscountAmount; // 최대 할인 한도 (nullable)
    private LocalDateTime expiredAt;
    private boolean isExpiringSoon;
}
