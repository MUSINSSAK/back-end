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
    // - JPQL이 아님 → 실제 DB 테이블/컬럼명을 그대로 사용해야 함
    // - select 절에서 별칭(alias) ↔ 인터페이스 getter 이름이 매칭됨
    @Query(value = """
        select
            r.id as id,               -- 리뷰 ID (Review PK)
            r.rating as rating,       -- 리뷰 별점 (1~5)
            u.nickname as authorName, -- 작성자 닉네임 (users.nickname)
            (case when exists (
                select 1 from review_images ri where ri.review_id = r.id
            ) then 1 else 0 end) as hasPhoto,
            r.content as content,     -- 리뷰 본문
            r.created_at as createdAt -- 리뷰 작성일
        from reviews r
            join users u on u.id = r.user_id -- 리뷰 작성자 조인
        where r.product_id = :productId     -- 특정 상품 리뷰만 조회
        """,
            countQuery = """
        select count(*) 
        from reviews r 
        where r.product_id = :productId     -- 페이징용 전체 개수
        """,
            nativeQuery = true)
    Page<ReviewListRow> findReviewRowsByProductId(@Param("productId") Long productId, Pageable pageable);

    interface ReviewListRow {
        Long getId();
        Integer getRating();
        String getAuthorName();
        Number getHasPhoto();    // Boolean → Number 로 변경 (중요), 안전한 타입
        String getContent();
        java.time.LocalDateTime getCreatedAt();
    }
}
