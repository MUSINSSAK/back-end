package com.example.musinssak.api.product;

import com.example.musinssak.api.product.dto.ProductListRequest;
import com.example.musinssak.api.product.dto.ProductMainResponse;
import com.example.musinssak.api.product.dto.ProductSearchRequest;
import com.example.musinssak.api.product.dto.ProductSearchResultResponse;
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
    public ResponseEntity<Map<String, Object>> getMainProducts() {
        List<ProductMainResponse> products = productService.getMainProducts();

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("code", "SUCCESS");
        response.put("message", "신규 상품 목록이 성공적으로 조회되었습니다.");
        response.put("data", Map.of("products", products));

        return ResponseEntity.ok(response);
    }

    /**
     * 키워드 기반 검색
     * 프론트 : brand=A&brand=B 가정
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(@ModelAttribute ProductSearchRequest request) {
        // 상품 목록 + 커서 포함된 응답
        ProductSearchResultResponse response = productService.searchProducts(request);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("code", "SUCCESS");
        result.put("message", "상품 검색 결과가 성공적으로 조회되었습니다.");
        result.put("data", response); // 전체 응답 구조를 담은 객체

        return ResponseEntity.ok(result);
    }

    /**
     * 카테고리 페이지 (검색 x)
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> listByCategory(@ModelAttribute ProductListRequest request) {
        ProductSearchResultResponse response = productService.listProducts(request);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("code", "SUCCESS");
        result.put("message", "상품 목록이 성공적으로 조회되었습니다.");
        result.put("data", response);
        return ResponseEntity.ok(result);
    }
}