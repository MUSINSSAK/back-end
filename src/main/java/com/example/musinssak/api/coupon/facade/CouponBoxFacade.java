package com.example.musinssak.api.coupon.facade;

import com.example.musinssak.api.coupon.dto.UserCouponsResponse;

public interface CouponBoxFacade {
    UserCouponsResponse getMyCoupons(Long userId);
}
