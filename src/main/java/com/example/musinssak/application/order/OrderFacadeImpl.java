// src/main/java/com/example/musinssak/application/order/OrderFacadeImpl.java
package com.example.musinssak.application.order;

import com.example.musinssak.application.order.command.CreateOrderCommand;
import com.example.musinssak.application.order.result.CreateOrderResult;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.cart.entity.CartItem;
import com.example.musinssak.domain.cart.repository.CartItemRepository;
import com.example.musinssak.domain.order.entity.OrderItem;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.repository.StockReservationRepository;
import com.example.musinssak.domain.order.service.OrdersService;
import com.example.musinssak.domain.order.service.StockReservationService;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.entity.ProductOption;
import com.example.musinssak.domain.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final CartItemRepository cartItemRepository;
    private final OrdersService ordersService;
    private final StockReservationService stockReservationService;

    // ▼ 추가
    private final ProductOptionRepository productOptionRepository;
    private final StockReservationRepository stockReservationRepository;

    @Override
    @Transactional
    public CreateOrderResult create(CreateOrderCommand command) {
        // 1) 파라미터 검증함
        if (command.getUserId() == null) {
            throw new BusinessException(ErrorCode.AUTH_REQUIRED);
        }
        if (command.getCartItemIds() == null || command.getCartItemIds().isEmpty()) {
            // 프로젝트에 없다면 INVALID_REQUEST 등으로 바꿔도 됨
            throw new BusinessException(ErrorCode.NO_SELECTED_ITEMS);
        }

        Long userId = command.getUserId();
        List<Long> ids = command.getCartItemIds();

        // 2) 내 소유의 장바구니 줄을 id로 조회함
        List<CartItem> cartItems = cartItemRepository.findByIdInAndCart_UserId(ids, userId);
        if (cartItems.isEmpty()) {
            throw new BusinessException(ErrorCode.NO_SELECTED_ITEMS);
        }

        // 3) 만료시각 계산함(현재 + 30분)
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30);

        // 4) 합계 및 스냅샷 준비함
        int totalProduct = 0;
        int totalDiscount = 0;
        int deliveryFee = 0;

        List<OrderItem> orderItems = new ArrayList<>();
        List<StockReservationService.ReservationPlan.ReservedItem> reservedItems = new ArrayList<>();
        List<CreateOrderResult.Item> respItems = new ArrayList<>();

        // 5) 줄 돌면서 (잠금 후) 재고 가용성 체크 + 금액 계산 + 스냅샷/예약/응답 채움
        for (CartItem ci : cartItems) {
            Long optionId = ci.getProductOption().getId();
            int qty = ci.getQuantity();

            // 5-1) 옵션 행을 잠금으로 읽음 (동시성 방지)
            ProductOption lockedOption = productOptionRepository.findForUpdateById(optionId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_OPTION_NOT_FOUND));
            Product product = lockedOption.getProduct();

            // 5-2) 유효 예약 수량 조회(만료 전만)
            long reserved = stockReservationRepository
                    .sumUnexpiredReservedQty(optionId, LocalDateTime.now());

            // 5-3) 가용 재고 = 실제 재고 - 유효 예약
            long available = (long) lockedOption.getStock() - reserved;
            if (available < qty) {
                // 재고 부족 → 바로 실패시킴
                throw new BusinessException(ErrorCode.OUT_OF_STOCK);
            }

            // 5-4) 금액 계산
            int price = product.getOriginalPrice();
            Integer discounted = product.getDiscountedPrice();
            int salePrice = (discounted != null) ? discounted : price;

            totalProduct += price * qty;
            totalDiscount += (price - salePrice) * qty;

            // 5-5) 주문 아이템 스냅샷
            orderItems.add(OrderItem.builder()
                    .product(product)
                    .productOptionId(optionId)
                    .quantity(qty)
                    .price(price)
                    .discountPrice(salePrice)
                    .build());

            // 5-6) 예약 계획
            reservedItems.add(StockReservationService.ReservationPlan.ReservedItem.builder()
                    .productOptionId(optionId)
                    .quantity(qty)
                    .build());

            // 5-7) 응답용
            respItems.add(CreateOrderResult.Item.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .brandName(product.getBrand().getName())
                    .size(lockedOption.getSize())
                    .quantity(qty)
                    .originalPrice(price)
                    .salePrice(salePrice)
                    .build());
        }

        // 6) 최종금액 계산함
        int finalAmount = totalProduct - totalDiscount + deliveryFee;

        // 7) 주문 저장함
        Orders saved = ordersService.createOrder(
                userId,
                OrderStatus.CREATED,
                totalProduct,
                totalDiscount,
                deliveryFee,
                finalAmount,
                expiresAt,
                orderItems
        );

        // 8) 재고 예약 생성함(만료시각 포함)
        stockReservationService.reserve(
                saved.getId(),
                userId,
                reservedItems,
                expiresAt
        );

        // 9) 성공이면 선택한 장바구니 줄 삭제함
        cartItemRepository.deleteByIdInAndCart_UserId(ids, userId);

        // 10) 결과 반환함
        return CreateOrderResult.builder()
                .orderPk(saved.getId())
                .orderNo(saved.getOrderNumber())
                .reservationExpires(expiresAt)
                .totalProductAmount(totalProduct)
                .discountAmount(totalDiscount)
                .deliveryFee(deliveryFee)
                .finalAmount(finalAmount)
                .items(respItems)
                .build();
    }
}
