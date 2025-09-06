package com.example.musinssak.common.util;

import com.example.musinssak.api.review.dto.SortType;
import org.springframework.data.domain.Sort;

// 정렬 규칙 유틸 (동점 시 최신 우선 포함)
public final class ReviewSorts {

    private ReviewSorts() {}

    /**
     * 리뷰 정렬 규칙:
     * latest: createdAt DESC
     * high  : rating DESC, createdAt DESC
     * low   : rating ASC,  createdAt DESC
     */
    /** 엔티티 기반(JPQL) 쓸 때만 사용 */
    public static Sort toJpaSort(SortType sortType) {
        return switch (sortType) {
            case LATEST -> Sort.by(Sort.Order.desc("createdAt"));
            case HIGH   -> Sort.by(
                    Sort.Order.desc("rating"),
                    Sort.Order.desc("createdAt")
            );
            case LOW    -> Sort.by(
                    Sort.Order.asc("rating"),
                    Sort.Order.desc("createdAt")
            );
        };
    }

    /** 네이티브 SQL(테이블 컬럼명)용 정렬 */
    public static Sort toNativeSort(SortType sortType) {
        return switch (sortType) {
            case LATEST -> Sort.by(Sort.Order.desc("created_at"));
            case HIGH   -> Sort.by(
                    Sort.Order.desc("rating"),
                    Sort.Order.desc("created_at")
            );
            case LOW    -> Sort.by(
                    Sort.Order.asc("rating"),
                    Sort.Order.desc("created_at")
            );
        };
    }
}