package com.example.musinssak.domain.coupon.repository;

import com.example.musinssak.domain.coupon.entity.UserCoupon;
import com.example.musinssak.domain.coupon.readmodel.UserCouponRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    /**
     * 사용자 보유 "유효" 쿠폰만 조회 (만료 제외)
     * - 만료 조건: c.expiredAt >= :now
     * - 엔티티 노출 대신 UserCouponRead 생성자 프로젝션으로 반환
     * - JOIN: user_coupons(uc) ↔ coupons(c)
     */
    @Query("""
        select new com.example.musinssak.domain.coupon.readmodel.UserCouponRead(
            c.id,
            c.name,
            c.description,
            c.type,
            c.discountRate,
            c.discountAmount,
            c.minOrderPrice,
            c.maxDiscountAmount,
            c.expiredAt
        )
        from UserCoupon uc
        join uc.coupon c
        where uc.userId = :userId
          and c.expiredAt >= :now
    """)
    List<UserCouponRead> findActiveByUserId(@Param("userId") Long userId,
                                            @Param("now") LocalDateTime now);
}
