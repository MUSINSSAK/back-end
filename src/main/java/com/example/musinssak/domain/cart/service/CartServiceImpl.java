package com.example.musinssak.domain.cart.service;

import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.cart.entity.Cart;
import com.example.musinssak.domain.cart.entity.CartItem;
import com.example.musinssak.domain.cart.repository.CartItemRepository;
import com.example.musinssak.domain.cart.repository.CartRepository;
import com.example.musinssak.domain.product.entity.ProductOption;
import com.example.musinssak.domain.product.repository.ProductOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductOptionRepository productOptionRepository;

    @Override
    public void addItem(Long userId, Long productOptionId, int quantity) {
        // 1) 수량 검증
        if (quantity < 1) throw new BusinessException(ErrorCode.CART_ITEM_QUANTITY_INVALID);

        // 2) 옵션 확인
        ProductOption option = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_OPTION_NOT_FOUND));

        // 3) 내 장바구니 찾거나 생성
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.ofUser(userId)));

        // 4) 같은 옵션이 이미 있는지 확인
        CartItem item = cartItemRepository.findByCartIdAndProductOptionId(cart.getId(), option.getId())
                .orElse(null);

        if (item == null) {
            // 새 줄 생성 (규칙 B: 기본 미선택)
            if (option.getStock() < quantity) throw new BusinessException(ErrorCode.OUT_OF_STOCK);
            CartItem newItem = CartItem.newItem(cart, option, quantity); // selected=false
            cartItemRepository.save(newItem);
        } else {
            // 수량 누적 + 재고 체크
            int newQty = item.getQuantity() + quantity;
            if (option.getStock() < newQty) throw new BusinessException(ErrorCode.OUT_OF_STOCK);
            item.changeQuantity(newQty);

            // 과거 데이터가 NULL이면 미선택으로 보정 (규칙 B 유지)
            item.markUnselectedIfNull();
        }
    }
}
