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
import com.example.musinssak.domain.order.service.OrdersService; // ← 복수형 서비스 사용함
import com.example.musinssak.domain.order.service.StockReservationService;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** 주문 생성 흐름을 조립함 */
@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final CartItemRepository cartItemRepository;           // 장바구니 줄 조회함
    private final OrdersService ordersService;                     // 주문 저장함(복수형)
    private final StockReservationService stockReservationService; // 재고 예약함

    /** 주문 생성함 */
    @Override
    @Transactional
    public CreateOrderResult create(CreateOrderCommand command) {
        // 1) 파라미터 검증함
        if (command.getUserId() == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED); // 로그인 필요함
        }
        if (command.getCartItemIds() == null || command.getCartItemIds().isEmpty()) {
            throw new BusinessException(ErrorCode.AUTH_REQUIRED); // 로그인 필요함
        }

        Long userId = command.getUserId();
        List<Long> ids = command.getCartItemIds();

        // 2) 내 소유의 장바구니 줄을 id로 조회함
        List<CartItem> cartItems =
                cartItemRepository.findByIdInAndCart_UserId(ids, userId);
        if (cartItems.isEmpty()) {
            throw new BusinessException(ErrorCode.NO_SELECTED_ITEMS); // 없으면 예외 던짐
        }

        // 3) 만료시각 계산함(현재 + 30분)
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30);

        // 4) 합계 및 스냅샷 준비함
        int totalProduct = 0;   // 총 원가 합임
        int totalDiscount = 0;  // 총 할인 합임
        int deliveryFee = 0;    // 배송비 0원임

        List<OrderItem> orderItems = new ArrayList<>(); // 주문아이템 스냅샷 목록임
        List<StockReservationService.ReservationPlan.ReservedItem> reservedItems = new ArrayList<>();

        // 5) 줄 돌면서 금액 계산하고 스냅샷/예약항목 채움
        for (CartItem ci : cartItems) {
            ProductOption option = ci.getProductOption(); // 옵션 꺼냄
            Product product = option.getProduct();        // 상품 꺼냄
            int qty = ci.getQuantity();                   // 수량 꺼냄

            int price = product.getOriginalPrice();                 // 원가 단가임
            Integer discounted = product.getDiscountedPrice();      // 할인가 단가일 수 있음
            int discountPrice = (discounted != null) ? discounted : price; // 없으면 원가임

            totalProduct += price * qty;                               // 원가 합 더함
            totalDiscount += (price - discountPrice) * qty;            // 할인 합 더함

            // 주문아이템 스냅샷 만듦
            orderItems.add(OrderItem.builder()
                    .productId(product.getId())
                    .productOptionId(option.getId())
                    .quantity(qty)
                    .price(price)
                    .discountPrice(discountPrice)
                    .build());

            // 예약항목 한 줄 추가함
            reservedItems.add(StockReservationService.ReservationPlan.ReservedItem.builder()
                    .productOptionId(option.getId())
                    .quantity(qty)
                    .build());
        }

        // 6) 최종금액 계산함
        int finalAmount = totalProduct - totalDiscount + deliveryFee;

        // 7) 주문 저장함(번호 발급 + 아이템 저장됨)
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

        // 8) 주문 id로 재고 예약 생성함
        stockReservationService.reserve(
                saved.getId(),
                userId,
                reservedItems,
                expiresAt
        );

        // 9) 결과 DTO 만들어 돌려줌
        return CreateOrderResult.builder()
                .orderNo(saved.getOrderNumber())        // 주문번호 넣음
                .reservationExpires(expiresAt)          // 만료시각 넣음
                .totalProductAmount(totalProduct)       // 총 원가 합 넣음
                .discountAmount(totalDiscount)          // 총 할인 합 넣음
                .deliveryFee(deliveryFee)               // 배송비 넣음
                .finalAmount(finalAmount)               // 최종 금액 넣음
                .build();                               // item 목록은 다음 단계에서 확장함
    }
}
