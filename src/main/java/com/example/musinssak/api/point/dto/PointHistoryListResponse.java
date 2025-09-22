package com.example.musinssak.api.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 적립금 내역 조회 응답 DTO
 * - points.total_amount 와 point_histories 목록을 조합
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointHistoryListResponse {
    private Integer totalPoint;   // 보유 총 적립금
    private List<PointHistoryItemResponse> histories; // 내역 리스트
}
