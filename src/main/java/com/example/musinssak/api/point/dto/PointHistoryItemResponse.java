package com.example.musinssak.api.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 적립금 내역 응답 DTO (단일 항목)
 * - point_histories 테이블의 row를 매핑
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointHistoryItemResponse {
    private Long historyId;       // 적립금 내역 ID
    private String type;          // CHARGE | USE | REFUND | EXPIRE
    private Integer amount;       // +적립 / -차감
    private String description;   // 사용자 노출 설명
    private LocalDate createdAt;  // 발생일 (YYYY-MM-DD)
    private LocalDate expiredAt;  // 만료일 (null 허용)
}
