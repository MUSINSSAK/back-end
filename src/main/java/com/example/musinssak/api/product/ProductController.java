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
     * 메인 화면 최신 상품 목록 10개
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
     * 키워드 기반 검색
     * 프론트 : brand=A&brand=B 가정
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
     * 카테고리 페이지 (검색 x)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<ProductSearchResultResponse>> listByCategory(@ModelAttribute ProductListRequest request) {
        ProductSearchResultResponse data = productService.listProducts(request);
        return ResponseEntity.ok(
                ApiResponse.success("상품 목록이 성공적으로 조회되었습니다.", data)
        );
    }
}