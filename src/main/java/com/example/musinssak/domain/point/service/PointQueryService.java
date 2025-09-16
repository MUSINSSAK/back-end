package com.example.musinssak.domain.point.service;

import com.example.musinssak.domain.point.projection.PointHistoryProjection;

import java.time.LocalDate;
import java.util.List;

/**
 * 포인트 조회 전용 서비스 포트 (읽기)
 */
public interface PointQueryService {

    /**
     * 사용자 총 적립금(points.total_amount)을 반환한다.
     * - 해당 사용자의 포인트 지갑(points row)이 없으면 BusinessException(POINT_NOT_FOUND) 발생
     */
    Integer getTotalPoint(Long userId);

    /**
     * 사용자 적립금 내역(point_histories)을 조회한다.
     * <p>
     * - startDate가 null이면 전체 조회
     * - startDate가 있으면 [startDate ~ endDateInclusive] 구간 조회
     * - created_at DESC(최신순) 정렬
     *
     * @param userId 사용자 ID
     * @param startDate 조회 시작일(포함), null이면 전체 조회
     * @param endDateInclusive 조회 종료일(포함)
     * @return 적립금 내역 Projection 목록
     */

    List<PointHistoryProjection> findHistories(Long userId, LocalDate startDate, LocalDate endDateInclusive);

}
