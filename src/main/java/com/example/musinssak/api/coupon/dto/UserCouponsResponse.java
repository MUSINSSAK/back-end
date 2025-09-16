package com.example.musinssak.api.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 사용자 쿠폰 목록 응답 DTO (상위 래퍼)
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponsResponse {
    private List<UserCouponResponse> coupons;
}
