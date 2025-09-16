package com.example.musinssak.api.coupon;

import com.example.musinssak.api.coupon.dto.UserCouponsResponse;
import com.example.musinssak.api.coupon.facade.CouponBoxFacade;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.infra.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (쿠폰함) 사용자 쿠폰 목록
 * GET /api/users/me/coupons
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CouponBoxController {

    private final CouponBoxFacade couponBoxFacade;     // 다음 단계에서 구현
    private final JwtTokenProvider jwtTokenProvider;   // 기존 방식 그대로 사용

    @GetMapping("/me/coupons")
    public ApiResponse<UserCouponsResponse> getMyCoupons(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long userId = Long.parseLong(jwtTokenProvider.getSubject(token));

        UserCouponsResponse data = couponBoxFacade.getMyCoupons(userId);

        return ApiResponse.success("사용자 쿠폰 목록이 성공적으로 조회되었습니다.", data);
    }
}
