package com.example.musinssak.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 팀 원칙: User 엔티티 직접참조 X → userId만 보관
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * DB에 is_expiring_soon 컬럼이 존재하지만,
     * - 만료일(expiredAt) 기반으로 "현재 시점(now)"에 따라 달라지는 파생 값
     * - API 응답 시점에만 계산해야 정확성 유지 가능
     * → 엔티티 필드에는 매핑하지 않음
     */
}
