package com.example.musinssak.domain.point.repository;

import com.example.musinssak.domain.point.entity.PointHistory;
import com.example.musinssak.domain.point.projection.PointHistoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 적립금 내역 조회용 리포지토리 (Projection 반환)
 */
public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    /** ALL: 기간 제한 없음 (최신순) */
    @Query("""
        select 
            h.id as id,
            h.type as type,
            h.amount as amount,
            h.description as description,
            h.createdAt as createdAt,
            h.expiredAt as expiredAt
        from PointHistory h
        join com.example.musinssak.domain.point.entity.Points p
          on h.pointId = p.id
       where p.userId = :userId
       order by h.createdAt desc
    """)
    List<PointHistoryProjection> findAllByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);

    /** 기간 지정: [start, end] 포함 (최신순) */
    @Query("""
        select 
            h.id as id,
            h.type as type,
            h.amount as amount,
            h.description as description,
            h.createdAt as createdAt,
            h.expiredAt as expiredAt
        from PointHistory h
        join com.example.musinssak.domain.point.entity.Points p
          on h.pointId = p.id
       where p.userId = :userId
         and h.createdAt between :start and :end
       order by h.createdAt desc
    """)
    List<PointHistoryProjection> findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            @Param("userId") Long userId,
            @Param("start") LocalDateTime startInclusive,
            @Param("end") LocalDateTime endInclusive
    );
}
