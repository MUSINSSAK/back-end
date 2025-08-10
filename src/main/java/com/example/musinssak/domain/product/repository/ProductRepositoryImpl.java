package com.example.musinssak.domain.product.repository;

import com.example.musinssak.api.product.dto.ProductListRequest;
import com.example.musinssak.api.product.dto.ProductSearchRequest;
import com.example.musinssak.api.product.dto.ProductSearchResponse;
import com.example.musinssak.api.product.dto.ProductSearchResultResponse;
import com.example.musinssak.domain.product.entity.QBrand;
import com.example.musinssak.domain.product.entity.QCategory;
import com.example.musinssak.domain.product.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory query;

    /**
     * [요청 파라미터 정리]
     * → where(검색어+필터)
     * → 커서(pivot 조회 → 정렬키+id 비교; pivot은 cursorId의 정렬키 값, cursor는 "지난 응답의 마지막 상품 id")
     * → orderBy(정렬키+id)  // 커서 비교식과 반드시 동일한 정렬 기준 사용
     * → select DTO 매핑 + join
     * → size+1 fetch  // 다음 페이지 존재 여부 판정
     * → hasNext/nextCursor 계산 → 응답  // nextCursor = 마지막 상품 id
     *
     * 프론트 규칙:
     * - 첫 페이지: cursor 생략(null)
     * - 다음 페이지: 직전 응답의 nextCursor를 cursor로 전달
     * - 정렬/필터(키워드·카테고리·브랜드·가격) 중 하나라도 바뀌면 리스트 초기화 & cursor=null
     */

    @Override // QueryDSL 로직
    public ProductSearchResultResponse searchProducts(ProductSearchRequest request) {
        // 1) 준비물 꺼내기 (Q클래스, 기본값)
        final int size = (request.getSize() == null || request.getSize() <= 0) ? 12 : request.getSize();
        final String sort = (request.getSort() == null) ? "recommend" : request.getSort().toLowerCase();

        // 2) where 조건 만들기 (검색어 + 필터)
        BooleanBuilder where = buildSearchWhereClause(request);

        return executeQuery(where, sort, size, request.getCursor());
    }

    @Override
    public ProductSearchResultResponse listProducts(ProductListRequest request) {
        // 1) 준비물 꺼내기 (Q클래스, 기본값)
        final int size = (request.getSize() == null || request.getSize() <= 0) ? 12 : request.getSize();
        final String sort = (request.getSort() == null) ? "recommend" : request.getSort().toLowerCase();

        // 2) where 조건 만들기 (카테고리 + 필터)
        BooleanBuilder where = buildListWhereClause(request);

        return executeQuery(where, sort, size, request.getCursor());
    }

    // searchProducts에 대한 Where절 생성
    private BooleanBuilder buildSearchWhereClause(ProductSearchRequest request) {
        QProduct p = QProduct.product;
        QBrand b = QBrand.brand;
        BooleanBuilder where = new BooleanBuilder();

        // 검색어 필수: 없으면 400으로 매핑할 예외 던짐 (ControllerAdvice 권장)
        if (request.getKeyword() == null || request.getKeyword().isBlank()) {
            throw new IllegalArgumentException("MISSING_KEYWORD");
        }
        // 포함 검색(대소문자 무시): 상품명에 키워드 포함
        where.and(p.name.containsIgnoreCase(request.getKeyword()));

        // 공통 필터 적용
        applyCommonFilters(where, request.getCategory(), request.getBrand(), request.getMinPrice(), request.getMaxPrice());
        return where;
    }

    // listProducts에 대한 Where절 생성
    private BooleanBuilder buildListWhereClause(ProductListRequest request) {
        BooleanBuilder where = new BooleanBuilder();
        // 공통 필터 적용
        applyCommonFilters(where, request.getCategory(), request.getBrand(), request.getMinPrice(), request.getMaxPrice());
        return where;
    }

    // 공통 필터(카테고리, 브랜드, 가격) 조건 추가
    private void applyCommonFilters(BooleanBuilder where, String category, List<String> brand, Integer minPrice, Integer maxPrice) {
        QProduct p = QProduct.product;
        QBrand b = QBrand.brand;
        QCategory c = QCategory.category;

        // 카테고리: DB Category.name 값과 정확히 일치해야 필터 적용
        if (category != null
                && !category.isBlank()
                && !"all".equalsIgnoreCase(category)
                && !"전체".equalsIgnoreCase(category)) {
            where.and(c.slug.eq(category));
        }
        // 브랜드 in (...) : &brand=나이키&brand=반스 반복 파라미터를 그대로 List<String>로 매핑
        if (brand != null && !brand.isEmpty()) {
            where.and(b.name.in(brand));
        }
        // 가격 범위 (할인 가격 기준)
        if (minPrice != null) where.and(p.discountedPrice.goe(minPrice));
        if (maxPrice != null) where.and(p.discountedPrice.loe(maxPrice));
    }

    // 중복 로직을 처리하는 공통 쿼리 실행 메서드
    private ProductSearchResultResponse executeQuery(BooleanBuilder where, String sort, int size, Long cursorId) {
        QProduct p = QProduct.product;
        QBrand b = QBrand.brand;
        QCategory c = QCategory.category;

        // 3) 커서 페이징 (무한 스크롤 핵심)
        // 안정 페이징을 위해 항상 "정렬키 + id" 쌍으로 비교한다.
        // 현재 요청 커서는 id(Long)만 오므로, 먼저 cursorId의 피벗(정렬키)을 조회해 비교식을 구성.
        if (cursorId != null) {
            switch (sort) {
                // [NEW/RECOMMEND] 최신/추천: createdAt DESC, id DESC 기준 다음 페이지 범위
                case "new", "recommend" -> {
                    LocalDateTime pivotCreatedAt = query.select(p.createdAt).from(p).where(p.id.eq(cursorId)).fetchOne();
                    if (pivotCreatedAt != null) {
                        where.and(p.createdAt.lt(pivotCreatedAt).or(p.createdAt.eq(pivotCreatedAt).and(p.id.lt(cursorId))));
                    } else {
                        where.and(p.id.lt(cursorId));
                    }
                }
                // [PRICE ASC] 가격 오름차순: price ASC, id ASC 기준 다음 페이지 범위
                case "priceasc" -> {
                    Integer pivotPrice = query.select(p.discountedPrice).from(p).where(p.id.eq(cursorId)).fetchOne();
                    if (pivotPrice != null) {
                        where.and(p.discountedPrice.gt(pivotPrice).or(p.discountedPrice.eq(pivotPrice).and(p.id.gt(cursorId))));
                    }
                }
                // [PRICE DESC] 가격 내림차순: price DESC, id DESC 기준 다음 페이지 범위
                case "pricedesc" -> {
                    Integer pivotPrice = query.select(p.discountedPrice).from(p).where(p.id.eq(cursorId)).fetchOne();
                    if (pivotPrice != null) {
                        where.and(p.discountedPrice.lt(pivotPrice).or(p.discountedPrice.eq(pivotPrice).and(p.id.lt(cursorId))));
                    }
                }
                // [POPULAR(임시)] 인기순(임시): createdAt DESC, id DESC와 동일한 커서 비교 적용
                case "popular" -> {
                    LocalDateTime pivotCreatedAt = query.select(p.createdAt).from(p).where(p.id.eq(cursorId)).fetchOne();
                    if (pivotCreatedAt != null) {
                        where.and(p.createdAt.lt(pivotCreatedAt).or(p.createdAt.eq(pivotCreatedAt).and(p.id.lt(cursorId))));
                    }
                }
                default -> { /* 미지정 정렬: 아래 orderBy의 default 처리에 위임 */ }
            }
        }

        // 4) 정렬 설정
        OrderSpecifier<?>[] orders = switch (sort) {
            case "new", "recommend" -> new OrderSpecifier[]{ p.createdAt.desc(), p.id.desc() };
            case "priceasc" -> new OrderSpecifier[]{ p.discountedPrice.asc(), p.id.asc() };
            case "pricedesc" -> new OrderSpecifier[]{ p.discountedPrice.desc(), p.id.desc() };
            case "popular" -> new OrderSpecifier[]{ p.createdAt.desc(), p.id.desc() }; // 임시
            default -> new OrderSpecifier[]{ p.createdAt.desc(), p.id.desc() };
        };

        // 5) SELECT + JOIN + 매핑
        List<ProductSearchResponse> list = query
                .select(Projections.constructor(ProductSearchResponse.class,
                        p.id,
                        b.name,             // brand
                        p.name,             // name
                        p.discountedPrice,  // price
                        p.originalPrice,    // originalPrice
                        p.discountRate,     // discount
                        c.name,             // category
                        p.thumbnailImageUrl // image
                ))
                .from(p)
                .join(p.brand, b)
                .join(p.category, c)
                .where(where)
                .orderBy(orders)
                .limit(size + 1L)
                .fetch();

        // 6) hasNext/nextCursor 계산
        boolean hasNext = list.size() > size;
        if (hasNext) {
            list = new ArrayList<>(list.subList(0, size));
        }

        Long nextCursor = null;
        if (hasNext && !list.isEmpty()) {
            nextCursor = list.get(list.size() - 1).getId();
        }

        return new ProductSearchResultResponse(list, nextCursor, hasNext);
    }
}