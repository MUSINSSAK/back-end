package com.example.musinssak.service;

import com.example.musinssak.domain.product.dto.ProductMainResponse;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j // 로그 사용을 위해 추가
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductMainResponse> getMainProducts() {
        List<Product> products = productRepository.findTop6ByOrderByCreatedAtDesc();

//        log.info("정제 전 URL = [{}]", rawUrl);
//        log.info("정제 후 URL = [{}]", sanitizedUrl);

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
}
