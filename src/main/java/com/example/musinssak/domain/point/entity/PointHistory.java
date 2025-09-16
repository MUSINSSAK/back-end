package com.example.musinssak.domain.point.entity;

import com.example.musinssak.domain.point.type.PointHistoryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "point_histories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** FK → points.id (키만 보관) */
    @Column(name = "point_id", nullable = false)
    private Long pointId;

    /** 타입: CHARGE | USE | REFUND | EXPIRE */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50, nullable = false)
    private PointHistoryType type;

    /** 금액: 적립(+) / 사용·소멸(-) */
    @Column(name = "amount", nullable = false)
    private Integer amount;

    /** 상세 사유 (DDL: TEXT) */
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    /** 발생 시각 (DDL: DATETIME) */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /** 만료 시각 (사용/소멸은 보통 null) */
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
}
