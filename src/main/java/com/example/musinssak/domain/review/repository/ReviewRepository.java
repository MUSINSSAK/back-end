package com.example.musinssak.domain.review.repository;

import com.example.musinssak.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 평균 평점 (없으면 null 반환)
    @Query("select avg(r.rating) from Review r where r.product.id = :productId")
    Double findAverageRatingByProductId(@Param("productId") Long productId);

    // 리뷰 개수
    long countByProduct_Id(Long productId);

    // 별점 분포 집계 (rating 별 개수 반환)
    @Query("""
       select r.rating, count(r.id)
       from Review r
       where r.product.id = :productId
       group by r.rating
       """)
    List<Object[]> countGroupByRating(@Param("productId") Long productId);

    // 네이티브 SQL (nativeQuery = true)
    @Query(value = """
        select
            r.id as id,
            r.rating as rating,
            u.nickname as authorName,
            (case when exists (
                select 1 from review_images ri where ri.review_id = r.id
            ) then 1 else 0 end) as hasPhoto,
            r.content as content,
            r.created_at as createdAt
        from reviews r
            join users u on u.id = r.user_id
        where r.product_id = :productId
        """,
            countQuery = """
        select count(*) 
        from reviews r 
        where r.product_id = :productId
        """,
            nativeQuery = true)
    Page<ReviewListRow> findReviewRowsByProductId(@Param("productId") Long productId, Pageable pageable);

    interface ReviewListRow {
        Long getId();
        Integer getRating();
        String getAuthorName();
        Number getHasPhoto();
        String getContent();
        java.time.LocalDateTime getCreatedAt();
    }

    // 특정 사용자가 작성한 모든 리뷰를 최신순으로 조회하는 메소드
    List<Review> findByUser_IdOrderByCreatedAtDesc(Long userId);

    // 특정 사용자가 리뷰를 작성한 모든 상품의 ID를 조회
    @Query("SELECT r.product.id FROM Review r WHERE r.user.id = :userId")
    List<Long> findReviewedProductIdsByUserId(@Param("userId") Long userId);
}