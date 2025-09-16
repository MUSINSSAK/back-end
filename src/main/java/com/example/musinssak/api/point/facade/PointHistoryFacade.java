package com.example.musinssak.api.point.facade;

import com.example.musinssak.api.point.dto.PointHistoryListResponse;
import com.example.musinssak.common.util.PeriodFilter;

public interface PointHistoryFacade {
    /**
     * 사용자 적립금 내역 조회 (총액 + 히스토리)
     *
     * @param userId 로그인 사용자 ID
     * @param filter 기간 필터 (1m | 3m | 6m | all)
     * @return 총액 및 히스토리 응답 DTO
     * @throws BusinessException USER_NOT_FOUND, POINT_NOT_FOUND 등
     */
    PointHistoryListResponse getPointHistories(Long userId, PeriodFilter filter);
}
