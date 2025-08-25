package com.example.musinssak.api.product;

import com.example.musinssak.api.product.dto.ProductListRequest;
import com.example.musinssak.api.product.dto.ProductMainResponse;
import com.example.musinssak.api.product.dto.ProductSearchRequest;
import com.example.musinssak.api.product.dto.ProductSearchResultResponse;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 메인 화면 최신 상품 목록 10개 조회
     * GET /api/products/main
     * @return 최신 상품 10개의 목록을 "products" 키로 담은 성공 응답
     */
    @GetMapping("/main")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMainProducts() {
        List<ProductMainResponse> products = productService.getMainProducts();

        // data 구조는 기존 명세서대로 "products" 키를 유지
        Map<String, Object> data = Map.of("products", products);
        return ResponseEntity.ok(
                ApiResponse.success("신규 상품 목록이 성공적으로 조회되었습니다.", data)
        );
    }

    /**
     * 키워드 기반 상품 검색 (무한 스크롤)
     * GET /api/products/search
     *
     * @param request 검색 조건 (키워드 + 브랜드, 카테고리, 가격 범위, 정렬, 커서 등)
     *                - 커서 기반 페이징: 마지막 상품 ID/정렬키 기준으로 다음 페이지 요청
     * @return 검색된 상품 목록과 커서 정보(hasNext, nextCursor)를 포함한 성공 응답
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<ProductSearchResultResponse>> searchProducts(@ModelAttribute ProductSearchRequest request) {
        // 상품 목록 + 커서 포함된 응답
        ProductSearchResultResponse data = productService.searchProducts(request);
        return ResponseEntity.ok(
                ApiResponse.success("상품 검색 결과가 성공적으로 조회되었습니다.", data)
        );
    }

    /**
     * 카테고리별 상품 목록 조회 (무한 스크롤, 검색어 없음)
     * GET /api/products
     *
     * @param request 조회 조건 (카테고리 + 브랜드, 가격 범위, 정렬, 커서 등)
     *                - 커서 기반 페이징: 마지막 상품 ID/정렬키 기준으로 다음 페이지 요청
     * @return 해당 카테고리 상품 목록과 커서 정보(hasNext, nextCursor)를 포함한 성공 응답
     */
    @GetMapping
    public ResponseEntity<ApiResponse<ProductSearchResultResponse>> listByCategory(@ModelAttribute ProductListRequest request) {
        ProductSearchResultResponse data = productService.listProducts(request);
        return ResponseEntity.ok(
                ApiResponse.success("상품 목록이 성공적으로 조회되었습니다.", data)
        );
    }
}