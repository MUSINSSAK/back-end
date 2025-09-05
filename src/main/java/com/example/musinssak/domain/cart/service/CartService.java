// src/main/java/com/example/musinssak/domain/cart/service/CartService.java
package com.example.musinssak.domain.cart.service;

import java.util.List;

/** 장바구니 커맨드 서비스임 */
public interface CartService {
    /** 담기임 */
    void addItem(Long userId, Long productOptionId, int quantity);

    /** 수량 변경임 (최종 수량으로 바꿈) */
    CartQuantityResult changeQuantity(Long userId, Long cartItemId, int newQuantity);

    /** 단건 삭제임 (X 버튼) */
    CartDeleteResult removeItem(Long userId, Long cartItemId);

    /** 선택 삭제임 (체크박스) */
    CartDeleteResult removeSelected(Long userId, List<Long> cartItemIds);
}
