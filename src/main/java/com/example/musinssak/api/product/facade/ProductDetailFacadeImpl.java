package com.example.musinssak.api.product.facade;

import com.example.musinssak.api.product.dto.ProductDetailResponse;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.entity.ProductImage;
import com.example.musinssak.domain.product.service.ProductQueryService;
import com.example.musinssak.domain.review.service.ReviewQueryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 여러 서비스 호출 + JSON 파싱/가공 + DTO 조립
 */
@Service
@RequiredArgsConstructor
public class ProductDetailFacadeImpl implements ProductDetailFacade {

    // Repository 대신 Service만 의존
    private final ProductQueryService productQueryService;
    private final ObjectMapper objectMapper;
    private final ReviewQueryService reviewQueryService;

    @Override
    @Transactional(readOnly = true)
    public ProductDetailResponse getProductDetail(Long productId) {
        // 1) 상품 + 브랜드 (없으면 서비스에서 예외)
        Product p = productQueryService.getProductWithBrandOrThrow(productId);

        // 2) 이미지 (sort_order ASC)
        List<String> images = productQueryService.getImages(productId).stream()
                .map(ProductImage::getImageUrl)
                .collect(toList());

        // 3) 옵션(사이즈/재고) (id ASC)
        List<ProductDetailResponse.SizeStock> sizes = productQueryService.getOptions(productId).stream()
                .map(opt -> ProductDetailResponse.SizeStock.builder()
                        .size(opt.getSize())
                        .stock(opt.getStock())
                        .build())
                .collect(toList());

        // 4) JSON 파싱 (features, materials, care)
        List<String> features = parseStringArray(p.getFeatures());
        List<String> care = parseStringArray(p.getCare());
        ProductDetailResponse.Materials materials = parseMaterials(p.getMaterials());

        // 5) 리뷰 집계
        Double avg = reviewQueryService.getAverageRating(productId);
        long count = reviewQueryService.getReviewCount(productId);

        // 소수 첫째 자리 반올림 (예: 4.6)
        double rating = (avg == null) ? 0.0 : Math.round(avg * 10.0) / 10.0;
        int reviewCount = Math.toIntExact(count);

        // 6) DTO 조립
        return ProductDetailResponse.builder()
                .productId(p.getId())
                .brandName(p.getBrand().getName())
                .productName(p.getName())
                .images(images)
                .originalPrice(p.getOriginalPrice())
                .discountRate(p.getDiscountRate())
                .discountedPrice(p.getDiscountedPrice())
                .rating(rating)
                .reviewCount(reviewCount)
                .sizes(sizes)
                .description(p.getDescription())
                .features(features)
                .materials(materials)
                .care(care)
                .build();
    }

    // ---------- JSON 파싱 헬퍼 ----------
    private List<String> parseStringArray(String json) {
        if (json == null || json.isBlank()) return Collections.emptyList();
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private ProductDetailResponse.Materials parseMaterials(String json) {
        if (json == null || json.isBlank()) {
            return ProductDetailResponse.Materials.builder()
                    .upper(Collections.emptyList())
                    .lining(Collections.emptyList())
                    .outsole(Collections.emptyList())
                    .build();
        }
        try {
            JsonNode node = objectMapper.readTree(json);
            return ProductDetailResponse.Materials.builder()
                    .upper(readArray(node.get("upper")))
                    .lining(readArray(node.get("lining")))
                    .outsole(readArray(node.get("outsole")))
                    .build();
        } catch (Exception e) {
            return ProductDetailResponse.Materials.builder()
                    .upper(Collections.emptyList())
                    .lining(Collections.emptyList())
                    .outsole(Collections.emptyList())
                    .build();
        }
    }

    private List<String> readArray(JsonNode arr) {
        if (arr == null || !arr.isArray()) return Collections.emptyList();
        List<String> list = new ArrayList<>();
        arr.forEach(n -> list.add(n.asText()));
        return list;
    }
}
