package com.example.musinssak.domain.point.repository;

import com.example.musinssak.domain.point.entity.Points;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 포인트 지갑(points) JPA 리포지토리
 *
 * - user_id로 1:1 매핑되는 row를 조회한다.
 * - 존재하지 않으면 Optional.empty()
 */
public interface PointsRepository extends JpaRepository<Points, Long> {

    Optional<Points> findByUserId(Long userId);
}
