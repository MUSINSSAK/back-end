package com.example.musinssak.domain.review.repository;

import com.example.musinssak.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 평균 평점 (없으면 null 반환)
    @Query("select avg(r.rating) from Review r where r.product.id = :productId")
    Double findAverageRatingByProductId(@Param("productId") Long productId);

    // 리뷰 개수
    long countByProduct_Id(Long productId);
}
