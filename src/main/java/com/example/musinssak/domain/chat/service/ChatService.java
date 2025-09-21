package com.example.musinssak.domain.chat.service;

import com.example.musinssak.api.chat.dto.AiServerRequest;
import com.example.musinssak.api.chat.dto.AiServerResponse;
import com.example.musinssak.api.chat.dto.ChatResponse;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final WebClient aiServerWebClient;
    private final ProductRepository productRepository;

    public ChatService(WebClient aiServerWebClient, ProductRepository productRepository) {
        this.aiServerWebClient = aiServerWebClient;
        this.productRepository = productRepository;
    }

    public ChatResponse getChatbotResponse(String query, String userId) {
        // 1. (생략) 사용자 정보 조회 및 AI 서버 요청 DTO 생성
        AiServerRequest.UserInfo userInfo = new AiServerRequest.UserInfo(
                userId, 175, 65, "male", List.of("P002"), List.of("P001")
        );
        AiServerRequest aiRequest = new AiServerRequest(query, userInfo);

        // 2. (생략) AI 서버 호출
        AiServerResponse aiResponse = aiServerWebClient.post()
                .uri("/api/v1/chat/completions")
                .bodyValue(aiRequest)
                .retrieve()
                .bodyToMono(AiServerResponse.class)
                .block();

        if (aiResponse == null || aiResponse.recommendedIds() == null || aiResponse.recommendedIds().isEmpty()) {
            String answer = (aiResponse != null) ? aiResponse.answer() : "죄송합니다, AI 서버 응답이 없습니다.";
            return new ChatResponse(answer, List.of());
        }

        // 3. AI가 추천한 ID 리스트를 Long 타입으로 변환 (순서 유지)
        List<Long> productIds = aiResponse.recommendedIds().stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        // 4. DB에서 상품 엔티티 목록 조회 (순서 보장 안 됨)
        List<Product> unorderedProducts = productRepository.findAllByIdIn(productIds);

        // 5. 조회된 상품들을 ID를 Key로 하는 Map으로 변환
        Map<Long, Product> productMap = unorderedProducts.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        // 6. AI가 추천한 ID 순서대로 Map에서 상품을 찾아 DTO로 변환 (최종 순서 보장)
        List<ChatResponse.ProductInfo> recommendedProducts = productIds.stream()
                .map(productMap::get)
                .filter(product -> product != null)
                .map(product -> new ChatResponse.ProductInfo(
                        product.getId().toString(),
                        product.getName(),                      // Lombok @Getter -> getName()
                        product.getBrand().getName(),           // Brand 객체에서 이름 가져오기
                        product.getDiscountedPrice(),           // 할인가 정보 사용
                        product.getThumbnailImageUrl(),         // 썸네일 이미지 URL 사용
                        "/products/" + product.getId() // 프론트엔드 경로에 맞춰 링크 직접 생성
                ))
                .collect(Collectors.toList());

        // 7. 최종 응답 DTO 조립 및 반환
        return new ChatResponse(aiResponse.answer(), recommendedProducts);
    }
}