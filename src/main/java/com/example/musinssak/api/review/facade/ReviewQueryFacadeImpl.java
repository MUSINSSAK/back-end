package com.example.musinssak.api.review.facade;

import com.example.musinssak.api.review.dto.ReviewItem;
import com.example.musinssak.api.review.dto.ReviewListResponse;
import com.example.musinssak.api.review.dto.SortType;
import com.example.musinssak.common.util.DateFormatters;
import com.example.musinssak.common.util.NameMasker;
import com.example.musinssak.common.util.RatingUtils;
import com.example.musinssak.common.util.ReviewSorts;
import com.example.musinssak.domain.product.service.ProductQueryService;
import com.example.musinssak.domain.review.repository.ReviewRepository;
import com.example.musinssak.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryFacadeImpl implements ReviewQueryFacade {

    // [1] 파사드는 "서비스들을 조립"하는 계층이므로, 비즈니스 로직을 가진 서비스에만 의존한다.
    private final ProductQueryService productQueryService; // 상품 존재 검증
    private final ReviewQueryService reviewQueryService;   // 리뷰 집계/목록 조회

    /**
     * 파사드에서 하는 일(조립 순서)
     *  1) 상품 존재 검증 (공통 예외핸들러에서 처리)
     *  2) 리뷰 "집계" 조회: 총개수, 평균(원시값), 별점 분포(1~5 정수키)
     *  3) 집계값을 API 명세 포맷으로 변환:
     *      - 평균 반올림(소수 1자리)
     *      - 분포 키를 문자열 "5"~"1"로 변환 + 누락키 0 채우기
     *  4) 정렬 규칙 생성(동점 시 최신우선) 후 "리뷰 목록 페이지" 조회
     *  5) 목록 Row -> 응답 DTO(ReviewItem)로 변환:
     *      - 작성자 이름 마스킹
     *      - 날짜 yyyy-MM-dd 포맷
     *  6) 최종 응답 DTO(ReviewListResponse) 조립:
     *      - page는 응답에서 1-based로 복원
     *      - hasNext는 Page.hasNext()로 세팅
     */
    @Override
    public ReviewListResponse getProductReviews(Long productId, SortType sortType,
                                                int zeroBasedPage, int size) {
        // 1) 상품 존재 검증 (없으면 404 예외)
        productQueryService.assertExists(productId);

        // 2-1) 총 리뷰 수 집계
        long totalReviews = reviewQueryService.getTotalCount(productId);

        // 2-2) 평균 별점(원시값) 조회 - null 가능
        Double avgRaw = reviewQueryService.getAverageRatingRaw(productId);

        // 2-3) 별점 분포 집계 (정수 키), 반환값 {5=89, 4=25, 3=13 ...}
        Map<Integer, Integer> distRaw = reviewQueryService.getRatingDistribution(productId);

        // 3) 집계값을 API 명세 포맷으로 변환
        double average = (avgRaw == null) ? 0.0 : RatingUtils.round1(avgRaw);
        Map<String, Integer> distribution = RatingUtils.fillDistribution(distRaw);

        // 4) 정렬 스펙 생성 → 목록 페이지 조회
        var sort = ReviewSorts.toNativeSort(sortType);
        Page<ReviewRepository.ReviewListRow> pageRows =
                reviewQueryService.getReviewPage(productId, zeroBasedPage, size, sort);

        // 5) 목록 Row -> 응답 DTO(ReviewItem)로 변환
        List<ReviewItem> items = pageRows.getContent().stream()
                .map(row -> {
                    boolean hasPhoto = row.getHasPhoto() != null && row.getHasPhoto().intValue() == 1;
                    return ReviewItem.builder()
                            .id(row.getId())
                            .rating(row.getRating())
                            .author(NameMasker.mask(row.getAuthorName()))
                            .hasPhoto(hasPhoto)                              // ← 여기!
                            .content(row.getContent())
                            .createdAt(DateFormatters.toYMD(row.getCreatedAt()))
                            .build();
                })
                .toList();


        // 6) 최종 응답 DTO 조립
        return ReviewListResponse.builder()
                .productId(productId)
                .totalReviews(totalReviews)
                .averageRating(average)
                .ratingDistribution(distribution)
                .reviews(items)
                .page(zeroBasedPage + 1)          // 응답에서는 1-based
                .size(size)
                .hasNext(pageRows.hasNext())      // 다음 페이지 여부
                .build();
    }
}
