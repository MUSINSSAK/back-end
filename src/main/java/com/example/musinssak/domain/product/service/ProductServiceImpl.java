package com.example.musinssak.domain.product.service;


import com.example.musinssak.api.product.dto.*;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductMainResponse> getMainProducts() {
        List<Product> products = productRepository.findTop10ByOrderByCreatedAtDesc();

        // Service 계층에서 직접 DTO로 변환
        return products.stream()
                .map(p -> new ProductMainResponse(
                        p.getId(),
                        p.getName(),
                        p.getBrand().getName(),
                        p.getThumbnailImageUrl(),
                        p.getOriginalPrice(),
                        p.getDiscountedPrice(),
                        p.getDiscountRate()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Custom Repository + Impl 사용 vs QueryDSL + Pageable(cursor 방식) v
     * 무한 스크롤 + 필터 + 정렬 + 조건
     */
    @Override
    public ProductSearchResultResponse searchProducts(ProductSearchRequest request) {

        // 상품 검색 요청: ProductSearchRequest(keyword=셔츠, category=all, brand=,
        // minPrice=null, maxPrice=null, sort=recommend, cursor=null, size=12)
        log.info("상품 검색 요청: {}", request);
        return productRepository.searchProducts(request);
    }

    @Override
    public ProductSearchResultResponse listProducts(ProductListRequest request) {
        log.info("상품 검색 요청: {}", request);
        return productRepository.listProducts(request);
    }
}
