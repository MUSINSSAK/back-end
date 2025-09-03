// src/main/java/com/example/musinssak/domain/cart/service/CartServiceImpl.java
package com.example.musinssak.domain.cart.service;

import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.cart.entity.Cart;
import com.example.musinssak.domain.cart.entity.CartItem;
import com.example.musinssak.domain.cart.repository.CartItemRepository;
import com.example.musinssak.domain.cart.repository.CartRepository;
import com.example.musinssak.domain.product.entity.ProductOption;
import com.example.musinssak.domain.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional // 트랜잭션 열림
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductOptionRepository productOptionRepository;

    @Override
    public void addItem(Long userId, Long productOptionId, int quantity) {
        if (quantity < 1) throw new BusinessException(ErrorCode.CART_ITEM_QUANTITY_INVALID); // 수량 검증함

        ProductOption option = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_OPTION_NOT_FOUND)); // 옵션 확인함

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.ofUser(userId))); // 카트 찾거나 만듦

        CartItem item = cartItemRepository.findByCartIdAndProductOptionId(cart.getId(), option.getId())
                .orElse(null); // 같은 옵션 줄 찾음

        if (item == null) {
            if (option.getStock() < quantity) throw new BusinessException(ErrorCode.OUT_OF_STOCK); // 재고 확인함
            CartItem newItem = CartItem.newItem(cart, option, quantity); // 새 줄 만듦 (미선택됨)
            cartItemRepository.save(newItem); // 저장함
        } else {
            int newQty = item.getQuantity() + quantity; // 누적 수량 계산함
            if (option.getStock() < newQty) throw new BusinessException(ErrorCode.OUT_OF_STOCK); // 재고 확인함
            item.changeQuantity(newQty); // 수량 바꿈
            item.markUnselectedIfNull(); // null 보정함
        }
    }

    @Override
    public CartQuantityResult changeQuantity(Long userId, Long cartItemId, int newQuantity) {
        if (newQuantity < 1) throw new BusinessException(ErrorCode.CART_ITEM_QUANTITY_INVALID); // 수량 검증함

        // 내 소유 줄을 옵션/상품까지 조인으로 가져옴
        CartItem item = cartItemRepository.findByIdAndUserIdJoinOption(cartItemId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CART_ITEM_NOT_FOUND)); // 없으면 예외 던짐

        int oldQty = item.getQuantity(); // 기존 수량 꺼냄
        ProductOption option = item.getProductOption(); // 옵션 꺼냄

        if (option.getStock() < newQuantity) throw new BusinessException(ErrorCode.OUT_OF_STOCK); // 재고 확인함

        item.changeQuantity(newQuantity); // 수량 바꿈

        int salePrice = option.getProduct().getDiscountedPrice(); // 세일가 꺼냄
        int availableStock = option.getStock(); // 현재 재고 꺼냄

        return new CartQuantityResult(item.getId(), oldQty, newQuantity, salePrice, availableStock); // 결과 돌려줌
    }
}
