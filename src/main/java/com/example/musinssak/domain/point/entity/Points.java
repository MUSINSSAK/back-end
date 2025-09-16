package com.example.musinssak.domain.point.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "points")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** FK → users.id (키만 보관) */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 현재 보유 총액 */
    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    /** 최신 갱신 시각 (DDL: DATETIME) */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
