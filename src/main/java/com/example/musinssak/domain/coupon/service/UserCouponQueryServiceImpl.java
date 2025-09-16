package com.example.musinssak.domain.coupon.service;

import com.example.musinssak.domain.coupon.readmodel.UserCouponRead;
import com.example.musinssak.domain.coupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회 최적화
public class UserCouponQueryServiceImpl implements UserCouponQueryService {
    private final UserCouponRepository userCouponRepository;

    @Override
    public List<UserCouponRead> findActiveCouponsByUserId(Long userId, LocalDateTime now) {
        // 만료 제외 로직은 레포지토리 JPQL에서 처리(c.expiredAt >= :now)
        return userCouponRepository.findActiveByUserId(userId, now);
    }
}
