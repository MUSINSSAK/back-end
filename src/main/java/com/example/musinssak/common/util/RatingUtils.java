package com.example.musinssak.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/*
    평균 별점 반올림/분포 보정 유틸
    DB에서 나온 “원시 집계값”(평균, 별점별 개수)을 API 명세 포맷(소수 첫째 자리 반올림 평균, "5"~"1" 문자열 키의 분포 맵)으로
    안전하게/일관되게 변환하는 표현 가공 유틸이에요. → 즉, “비즈니스 결과”를 “클라이언트가 보기 좋은 형태”로 바꿀 때 씁니다.
 */
public final class RatingUtils {

    private RatingUtils(){}

    /** 소수 첫째 자리 반올림 (예: 4.56 -> 4.6) */
    public static double round1(double value) {
        return BigDecimal.valueOf(value).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    /** 분포 맵을 "5"~"1" 키로 모두 채워 반환(누락시 0) */
    public static Map<String, Integer> fillDistribution(Map<Integer, Integer> raw) {
        Map<String, Integer> result = new HashMap<>();
        for (int r = 5; r >= 1; r--) {
            int count = raw == null ? 0 : raw.getOrDefault(r, 0);
            result.put(String.valueOf(r), count);
        }
        return result;
    }
}