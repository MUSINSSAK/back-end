package com.example.musinssak.api.coupon.facade;

import com.example.musinssak.api.coupon.dto.UserCouponResponse;
import com.example.musinssak.api.coupon.dto.UserCouponsResponse;
import com.example.musinssak.api.coupon.facade.CouponBoxFacade;
import com.example.musinssak.domain.coupon.readmodel.UserCouponRead;
import com.example.musinssak.domain.coupon.service.UserCouponQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponBoxFacadeImpl implements CouponBoxFacade {

    private final UserCouponQueryService userCouponQueryService;

    @Override
    public UserCouponsResponse getMyCoupons(Long userId) {
        LocalDateTime now = LocalDateTime.now();

        // 1) 유효 쿠폰 조회
        List<UserCouponRead> reads = userCouponQueryService.findActiveCouponsByUserId(userId, now);

        // 2) ReadModel -> DTO 매핑 (+ isExpiringSoon 계산)
        List<UserCouponResponse> items = reads.stream()
                .map(r -> UserCouponResponse.builder()
                        .couponId(r.getCouponId())
                        .couponName(r.getCouponName())
                        .couponDescription(r.getCouponDescription())
                        .couponType(r.getCouponType().name()) // enum -> String
                        .discountRate(r.getDiscountRate())
                        .discountAmount(r.getDiscountAmount())
                        .minOrderPrice(r.getMinOrderPrice())
                        .maxDiscountAmount(r.getMaxDiscountAmount())
                        .expiredAt(r.getExpiredAt())
                        .isExpiringSoon(isExpiringSoon(r.getExpiredAt(), now))
                        .build())
                .toList();

        // 3) 정렬: 임박(true 먼저) → 만료일 오름차순(더 빨리 만료되는 것 먼저) → 할인율(PERCENT만) 내림차순 → 이름 오름차순
        items = items.stream()
                .sorted(Comparator
                        // 1) 임박(true) 먼저
                        .comparing(UserCouponResponse::isExpiringSoon, Comparator.reverseOrder())
                        // 2) 만료일 오름차순 (null 안전 처리: null은 가장 뒤로)
                        .thenComparing(UserCouponResponse::getExpiredAt,
                                Comparator.nullsLast(LocalDateTime::compareTo))
                        // .thenComparing(UserCouponResponse::getExpiredAt)
                        // 3) 퍼센트 쿠폰의 할인율 내림차순 (AMOUNT는 0으로 취급)
                        .thenComparing(r -> "PERCENT".equals(r.getCouponType())
                                        ? (r.getDiscountRate() != null ? r.getDiscountRate() : 0)
                                        : 0,
                                Comparator.reverseOrder())
                        // 4) 이름 오름차순
                        .thenComparing(UserCouponResponse::getCouponName,
                                Comparator.nullsLast(String::compareTo)))
                .toList();

        // 4) 래핑 후 반환
        return new UserCouponsResponse(items);
    }

    // 만료 임박(3일 이하) 판단
    private boolean isExpiringSoon(LocalDateTime expiredAt, LocalDateTime now) {
        if (expiredAt == null) return false;        // 만료일 없음 → 임박 아님
        if (expiredAt.isBefore(now)) return false;  // 이미 만료 → 임박 아님
        long days = Duration.between(now, expiredAt).toDays(); // now 기준 남은 일수
        return days <= 3; // 오늘 포함 3일 이하면 true
    }
}
