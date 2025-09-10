// src/main/java/com/example/musinssak/api/order/OrderController.java
package com.example.musinssak.api.order;

import com.example.musinssak.api.order.dto.OrderCreateRequest;
import com.example.musinssak.api.order.dto.OrderCreateResponse;
import com.example.musinssak.application.order.OrderFacade;
import com.example.musinssak.application.order.command.CreateOrderCommand;
import com.example.musinssak.application.order.result.CreateOrderResult;
import com.example.musinssak.common.web.ApiResponse; // 우리 공통 응답 래퍼임
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
// ⚠ Swagger ApiResponse는 이름 충돌남 -> import 안함, FQN으로 씀

/**
 * 주문 컨트롤러임
 * - 요청/응답만 처리함
 * - 비즈니스는 파사드/서비스가 함
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

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
                .orderId(result.getOrderNo())
                .reservationExpiresAt(result.getReservationExpires())
                .totalProductAmount(result.getTotalProductAmount())
                .discountAmount(result.getDiscountAmount())
                .deliveryFee(result.getDeliveryFee())
                .finalAmount(result.getFinalAmount())
                .orderItems(null) // 다음 단계에서 채움
                .build();

        // 공통 포맷으로 감싸서 반환함
        return ApiResponse.success(resp);
    }

    // 인증에서 userId 꺼냄
    private Long getUserIdOrThrow(Authentication authentication) {
        try {
            return Long.parseLong(authentication.getName());
        } catch (Exception e) {
            throw new IllegalStateException("인증에서 userId를 찾을 수 없음");
        }
    }
}
