package com.example.musinssak.domain.coupon.service;

import com.example.musinssak.domain.coupon.readmodel.UserCouponRead;

import java.time.LocalDateTime;
import java.util.List;

public interface UserCouponQueryService {
    List<UserCouponRead> findActiveCouponsByUserId(Long userId, LocalDateTime now);
}
