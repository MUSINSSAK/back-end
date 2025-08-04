package com.example.musinssak.controller;

import com.example.musinssak.domain.product.dto.ProductMainResponse;
import com.example.musinssak.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
