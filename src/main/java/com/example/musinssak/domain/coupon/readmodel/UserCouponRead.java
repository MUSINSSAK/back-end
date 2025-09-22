package com.example.musinssak.domain.coupon.readmodel;

import com.example.musinssak.domain.coupon.type.CouponType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 도메인 레이어의 조회 전용 모델 (ReadModel, Projection)
 * - Repository/Service → Facade로 전달할 때 사용
 * - API DTO(UserCouponResponse)와는 별도의 책임
 */
@Getter
@NoArgsConstructor
public class UserCouponRead {

    private Long couponId;
    private String couponName;
    private String couponDescription;
    private CouponType couponType;     // enum (PERCENT, AMOUNT, FREE_DELIVERY)
    private Integer discountRate;      // nullable
    private Integer discountAmount;    // nullable
    private Integer minOrderPrice;
    private Integer maxDiscountAmount; // nullable
    private LocalDateTime expiredAt;

    public UserCouponRead(Long couponId,
              String couponName,
              String couponDescription,
              CouponType couponType,
              Integer discountRate,
              Integer discountAmount,
              Integer minOrderPrice,
              Integer maxDiscountAmount,
              LocalDateTime expiredAt) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.couponDescription = couponDescription;
        this.couponType = couponType;
        this.discountRate = discountRate;
        this.discountAmount = discountAmount;
        this.minOrderPrice = minOrderPrice;
        this.maxDiscountAmount = maxDiscountAmount;
        this.expiredAt = expiredAt;
    }
}
