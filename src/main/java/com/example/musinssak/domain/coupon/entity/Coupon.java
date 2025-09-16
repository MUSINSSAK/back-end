package com.example.musinssak.domain.coupon.entity;

import com.example.musinssak.domain.coupon.type.CouponType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50)
    private CouponType type;

    @Column(name = "discount_rate")
    private Integer discountRate;       // nullable

    @Column(name = "discount_amount")
    private Integer discountAmount;     // nullable

    @Column(name = "min_order_price")
    private Integer minOrderPrice;

    @Column(name = "max_discount_amount")
    private Integer maxDiscountAmount;  // nullable

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
}
