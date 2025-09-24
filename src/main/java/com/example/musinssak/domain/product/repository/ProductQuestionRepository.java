package com.example.musinssak.domain.product.repository;

import com.example.musinssak.domain.product.entity.ProductQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductQuestionRepository extends JpaRepository<ProductQuestion, Long> {

    long countByProductId(Long productId);

    // 최신순
    @Query(
            value = "SELECT q FROM ProductQuestion q " +
                    "WHERE q.product.id = :productId " +
                    "ORDER BY q.createdAt DESC",
            countQuery = "SELECT COUNT(q) FROM ProductQuestion q WHERE q.product.id = :productId"
    )
    Page<ProductQuestion> findLatestByProductId(@Param("productId") Long productId, Pageable pageable);

    // 답변대기 우선순 (PENDING 먼저, 그 다음 최신순)
    @Query(
            value = "SELECT q FROM ProductQuestion q " +
                    "WHERE q.product.id = :productId " +
                    "ORDER BY CASE WHEN q.status = 'PENDING' THEN 0 ELSE 1 END, q.createdAt DESC",
            countQuery = "SELECT COUNT(q) FROM ProductQuestion q WHERE q.product.id = :productId"
    )
    Page<ProductQuestion> findPendingFirstByProductId(@Param("productId") Long productId, Pageable pageable);
}