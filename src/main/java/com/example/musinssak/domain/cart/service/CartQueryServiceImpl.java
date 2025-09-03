// src/main/java/com/example/musinssak/domain/cart/service/CartQueryServiceImpl.java
package com.example.musinssak.domain.cart.service;

import com.example.musinssak.api.cart.dto.CartGetResponse;
import com.example.musinssak.api.cart.dto.CartItemDto;
import com.example.musinssak.domain.cart.entity.Cart;
import com.example.musinssak.domain.cart.repository.CartItemRepository;
import com.example.musinssak.domain.cart.repository.CartRepository;
import com.example.musinssak.domain.cart.repository.view.CartItemRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용 트랜잭션 열림
public class CartQueryServiceImpl implements CartQueryService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartGetResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null); // 카트 찾음

        if (cart == null) { // 없으면 빈 응답 돌려줌
            return new CartGetResponse(userId, Collections.emptyList(), 0,0,0,0,0,0,0);
        }

        List<CartItemRow> rows = cartItemRepository.findRowsForCart(cart.getId()); // 조인 조회함

        List<CartItemDto> items = rows.stream().map(r -> new CartItemDto(
                r.getCartItemId(),
                r.getProductId(),
                r.getProductName(),
                r.getBrandName(),
                r.getImageUrl(),
                r.getSize(),
                r.getQuantity(),
                r.getOriginalPrice(),
                r.getSalePrice(),
                r.getDiscountRate(),
                r.isSelected(),
                r.getStock()
        )).toList(); // 화면용 DTO로 바꿈

        int totalCount = items.size(); // 전체 줄 수 계산함
        int totalPrice = items.stream().mapToInt(i -> i.getOriginalPrice() * i.getQuantity()).sum(); // 원가 합 계산함
        int selectedCount = (int) items.stream().filter(CartItemDto::isSelected).count(); // 선택 개수 계산함
        int selectedSaleSum = items.stream().filter(CartItemDto::isSelected)
                .mapToInt(i -> i.getSalePrice() * i.getQuantity()).sum(); // 선택 세일가 합 계산함
        int selectedOriginSum = items.stream().filter(CartItemDto::isSelected)
                .mapToInt(i -> i.getOriginalPrice() * i.getQuantity()).sum(); // 선택 원가 합 계산함
        int discountAmount = selectedOriginSum - selectedSaleSum; // 할인 합 계산함
        int deliveryFee = 0; // 배송비 0원 둠
        int finalAmount = selectedSaleSum + deliveryFee; // 최종금액 계산함

        return new CartGetResponse(
                userId, items,
                selectedCount, selectedSaleSum,
                totalCount, totalPrice,
                discountAmount, deliveryFee, finalAmount
        );
    }
}
