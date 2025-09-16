package com.example.musinssak.api.point.facade;

import com.example.musinssak.api.point.dto.PointHistoryItemResponse;
import com.example.musinssak.api.point.dto.PointHistoryListResponse;
import com.example.musinssak.common.util.PeriodFilter;
import com.example.musinssak.domain.point.projection.PointHistoryProjection;
import com.example.musinssak.domain.point.service.PointQueryService;
import com.example.musinssak.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

/**
 * 적립금 내역 조회 파사드 (스켈레톤)
 * - 다음 단계에서 내부 로직을 한 줄씩 채워나간다.
 */
@Component
@RequiredArgsConstructor
public class PointHistoryFacadeImpl implements PointHistoryFacade {

    private final UserService userService;   // 기존 서비스 인터페이스에 의존
    private final PointQueryService pointQueryService;
    private final Clock clock; // 테스트 용이성(시간 주입)

    @Override
    public PointHistoryListResponse getPointHistories(Long userId, PeriodFilter filter) {
        // Step 2: 사용자 검증 (없으면 USER_NOT_FOUND 예외 전파)
        userService.getUserById(userId);

        // Step 3: 기간 → 날짜 범위 계산
        LocalDate today = LocalDate.now(clock);
        LocalDate startDate = filter.calcStartDateOrNull(today);
        // startDate == null 이면 'ALL'(기간 제한 없음) 의미

        // Step 4: 총액 조회 (없으면 POINT_NOT_FOUND)
        Integer totalPoint = pointQueryService.getTotalPoint(userId);

        // Step 5: 히스토리 조회 (기간/정렬 적용)
        List<PointHistoryProjection> rows = pointQueryService.findHistories(userId, startDate, today);

        // Step 6: Projection → 응답 DTO 매핑
        List<PointHistoryItemResponse> items = rows.stream()
                .map(p -> PointHistoryItemResponse.builder()
                        .historyId(p.getId())
                        .type(p.getType().name())                       // Enum이면 .name()
                        .amount(p.getAmount())
                        .description(p.getDescription())
                        .createdAt(p.getCreatedAt().toLocalDate())      // LocalDateTime → LocalDate
                        .expiredAt(p.getExpiredAt() != null ? p.getExpiredAt().toLocalDate() : null)
                        .build())
                .toList();

        return PointHistoryListResponse.builder()
                .totalPoint(totalPoint)
                .histories(items)
                .build();
    }
}
