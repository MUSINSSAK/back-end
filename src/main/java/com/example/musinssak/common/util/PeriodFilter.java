package com.example.musinssak.common.util;

import java.time.LocalDate;

/**
 * 기간 필터 파라미터 유틸
 *
 * - 클라이언트가 전달한 "period" 쿼리값(1m, 3m, 6m, all)을 Enum으로 매핑한다.
 * - ALL 은 기간 제한 없음(null 반환).
 * - 서비스/파사드 단에서는 calcStartDateOrNull(today) 로 시작일을 계산해 사용한다.
 *
 * 사용 예시:
 *   PeriodFilter filter = PeriodFilter.from("3m");
 *   LocalDate start = filter.calcStartDateOrNull(LocalDate.now());
 */
public enum PeriodFilter {
    ALL, M1, M3, M6;

    public static PeriodFilter from(String raw) {
        if (raw == null) return ALL;
        String v = raw.trim().toLowerCase();
        return switch (v) {
            case "1m" -> M1;
            case "3m" -> M3;
            case "6m" -> M6;
            case "all" -> ALL;
            default -> ALL; // 정의 외 값은 관용적으로 ALL 처리
        };
    }

    /** [start, today] 범위를 계산. ALL 은 start=null 의미(필터 없음) */
    public LocalDate calcStartDateOrNull(LocalDate today) {
        return switch (this) {
            case ALL -> null;
            case M1 -> today.minusMonths(1);
            case M3 -> today.minusMonths(3);
            case M6 -> today.minusMonths(6);
        };
    }
}
