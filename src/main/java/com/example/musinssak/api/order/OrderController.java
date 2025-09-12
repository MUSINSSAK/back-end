// src/main/java/com/example/musinssak/api/order/OrderController.java
package com.example.musinssak.api.order;

import com.example.musinssak.api.order.dto.OrderCreateRequest;
import com.example.musinssak.api.order.dto.OrderCreateResponse;
import com.example.musinssak.api.order.dto.OrderItemsResponse;
import com.example.musinssak.application.order.OrderFacade;
import com.example.musinssak.application.order.OrderQueryService;
import com.example.musinssak.application.order.command.CreateOrderCommand;
import com.example.musinssak.application.order.result.CreateOrderResult;
import com.example.musinssak.common.web.ApiResponse; // 공통 응답 래퍼임
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
// ⚠ Swagger ApiResponse는 이름 충돌 가능함 → FQN으로 씀

/**
 * 주문 컨트롤러임
 * - 요청/응답만 처리함
 * - 비즈니스는 파사드/서비스가 함
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;           // 주문 생성 파사드 주입됨
    private final OrderQueryService orderQueryService; // 주문 조회 서비스 주입됨

    /**
     * [POST] /api/orders/create
     * - 장바구니 선택 상품으로 주문 생성함
     * - 재고예약/만료시간 등은 파사드가 처리함
     */
    @Operation(summary = "주문 생성", description = "장바구니 선택 상품으로 주문 생성함")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/create")
    public ApiResponse<OrderCreateResponse> createOrder(
            @Valid @RequestBody OrderCreateRequest req,
            Authentication authentication
    ) {
        Long userId = getUserIdOrThrow(authentication); // 유저 id 꺼냄

        // 파사드 커맨드 만듦
        CreateOrderCommand command = CreateOrderCommand.builder()
                .userId(userId)
                .cartItemIds(req.getCartItemIds())
                .build();

        // 파사드 호출함
        CreateOrderResult result = orderFacade.create(command);

        // 응답 DTO로 매핑함
        OrderCreateResponse resp = OrderCreateResponse.builder()
                .orderId(result.getOrderNo())                        // 주문번호임
                .reservationExpiresAt(result.getReservationExpires())// 만료시각임
                .totalProductAmount(result.getTotalProductAmount())  // 총 원가 합임
                .discountAmount(result.getDiscountAmount())          // 총 할인 합임
                .deliveryFee(result.getDeliveryFee())                // 배송비임
                .finalAmount(result.getFinalAmount())                // 최종금액임
                .orderItems(
                        result.getItems() == null ? null :           // 널이면 널로 둠
                                result.getItems().stream()
                                        .map(it -> OrderCreateResponse.Item.builder()
                                                .productId(it.getProductId())
                                                .productName(it.getProductName())
                                                .brandName(it.getBrandName())
                                                .size(it.getSize())
                                                .quantity(it.getQuantity())
                                                .originalPrice(it.getOriginalPrice())
                                                .salePrice(it.getSalePrice())
                                                .build()
                                        ).toList()
                )
                .build();

        // 공통 포맷으로 감싸서 반환함
        return ApiResponse.success(resp);
    }

    /**
     * [GET] /api/orders/{orderId}/items
     * - 주문 상품 목록과 합계를 내려줌
     * - 내 주문만 조회 가능함(소유자 검증함)
     * - 가격은 주문 시점 스냅샷(oi.price/oi.discountPrice)로 내려줌
     */
    @Operation(summary = "주문 상품 조회", description = "주문 ID로 상품 목록과 합계를 내려줌(내 주문만 가능함)")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/{orderId}/items")
    public ApiResponse<OrderItemsResponse> getOrderItems(
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        Long userId = getUserIdOrThrow(authentication); // 유저 id 꺼냄
        // 서비스 호출해서 응답 만들음
        OrderItemsResponse body = orderQueryService.getOrderItems(orderId, userId);
        return ApiResponse.success(body); // 공통 포맷으로 감싸서 반환함
    }

    /** 인증에서 userId 꺼냄 */
    private Long getUserIdOrThrow(Authentication authentication) {
        try {
            return Long.parseLong(authentication.getName()); // 문자열을 숫자로 바꿈
        } catch (Exception e) {
            throw new IllegalStateException("인증에서 userId를 찾을 수 없음"); // 못 찾으면 예외 던짐
        }
    }
}
