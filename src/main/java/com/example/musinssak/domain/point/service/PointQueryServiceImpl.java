package com.example.musinssak.domain.point.service;

import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.point.entity.Points;
import com.example.musinssak.domain.point.projection.PointHistoryProjection;
import com.example.musinssak.domain.point.repository.PointHistoryRepository;
import com.example.musinssak.domain.point.repository.PointsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 포인트 조회 전용 서비스 구현 (JPA)
 * - 총액 미존재 시 이 레이어에서 BusinessException(POINT_NOT_FOUND) 처리
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointQueryServiceImpl implements PointQueryService {

    private final PointsRepository pointsRepository;
    private final PointHistoryRepository pointHistoryRepository;

    // 사용자 총 적립금(points.total_amount)을 반환한다.
    @Override
    public Integer getTotalPoint(Long userId) {
        // points.user_id 1:1 가정. 없으면 POINT_NOT_FOUND
        Points wallet = pointsRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POINT_NOT_FOUND));
        return wallet.getTotalAmount();
    }

    // 사용자 적립금 내역(point_histories)을 조회한다.
    @Override
    public List<PointHistoryProjection> findHistories(Long userId, LocalDate startDate, LocalDate endDateInclusive) {
        if (startDate == null) {
            // ALL: 기간 제한 없음, 최신순
            return pointHistoryRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        }
        // [start 00:00:00, end 23:59:59.999999999] 포함 범위
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDateInclusive.atTime(LocalTime.MAX);
        return pointHistoryRepository.findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(userId, start, end);
    }
}
