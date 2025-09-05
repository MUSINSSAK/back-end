// src/main/java/com/example/musinssak/domain/cart/service/CartServiceImpl.java
package com.example.musinssak.domain.cart.service;

import com.example.musinssak.api.cart.dto.CartSelectResponse;
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


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductOptionRepository productOptionRepository;

    // ====== 기존 addItem / changeQuantity는 네가 이미 구현했으니 그대로 둠 ======
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
    // ===============================================================

    /** X 버튼 단건 삭제임 */
    @Override
    public CartDeleteResult removeItem(Long userId, Long cartItemId) {
        // 1) 내 장바구니가 있는지 확인함
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CART_ITEM_NOT_FOUND)); // 장바구니 없으면 못 지움

        // 2) 내 소유인 줄만 지움 (타인 소유면 안됨)
        long deleted = cartItemRepository.deleteByIdAndCart_UserId(cartItemId, userId);
        if (deleted == 0) {
            // 대상이 없거나, 내 것이 아니면 실패로 알려줌
            throw new BusinessException(ErrorCode.CART_ITEM_NOT_FOUND);
        }

        // 3) 남은 줄 개수 셈
        long remain = cartItemRepository.countByCart_UserId(userId);

        // 4) 결과 돌려줌
        return new CartDeleteResult((int) deleted, (int) remain);
    }

    /** 체크박스 선택 삭제임 */
    @Override
    public CartDeleteResult removeSelected(Long userId, List<Long> cartItemIds) {
        if (cartItemIds == null || cartItemIds.isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST); // 비어있으면 안됨
        }

        // 1) 중복 아이디가 올 수 있어서 Set으로 바꿔서 중복 제거함
        Set<Long> uniqueIds = new HashSet<>(cartItemIds);

        // 2) 내 소유인 줄만 여러 개 지움 (타인 소유/없는 아이디는 자동으로 무시됨)
        long deleted = cartItemRepository.deleteByIdInAndCart_UserId(uniqueIds, userId);

        // 3) 남은 줄 개수 셈
        long remain = cartItemRepository.countByCart_UserId(userId);

        // 4) 결과 돌려줌 (부분 성공이어도 몇 개 지워졌는지 알려줌)
        return new CartDeleteResult((int) deleted, (int) remain);
    }

    // ======= 신규: 선택/해제 =======

    @Override
    public CartSelectResponse setSelectedBulk(Long userId, List<Long> cartItemIds, boolean isSelected) {
        if (cartItemIds == null || cartItemIds.isEmpty())
            throw new BusinessException(ErrorCode.INVALID_REQUEST);

        Set<Long> uniqueIds = new HashSet<>(cartItemIds);
        cartItemRepository.updateSelectedByIdsAndUserId(uniqueIds, userId, isSelected);

        int count = cartItemRepository.countSelectedByUserId(userId);
        Integer sum = cartItemRepository.sumSelectedPriceByUserId(userId);
        return new CartSelectResponse(count, sum == null ? 0 : sum);
    }

    @Override
    public CartSelectResponse setSelectedAll(Long userId, boolean isSelected) {
        cartItemRepository.updateSelectedAllByUserId(userId, isSelected);

        int count = cartItemRepository.countSelectedByUserId(userId);
        Integer sum = cartItemRepository.sumSelectedPriceByUserId(userId);
        return new CartSelectResponse(count, sum == null ? 0 : sum);
    }
}
