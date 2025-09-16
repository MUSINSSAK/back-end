package com.example.musinssak.domain.point.projection;

import com.example.musinssak.domain.point.type.PointHistoryType;

import java.time.LocalDateTime;

/** Projection: DB에서 필요한 컬럼만 얇게 조회 */
public interface PointHistoryProjection {
    Long getId();
    PointHistoryType getType();
    Integer getAmount();
    String getDescription();
    LocalDateTime getCreatedAt(); // DATETIME
    LocalDateTime getExpiredAt(); // DATETIME (nullable)
}
