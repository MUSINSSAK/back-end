// src/main/java/com/example/musinssak/api/order/OrderController.java
package com.example.musinssak.api.order;

import com.example.musinssak.api.order.dto.OrderCreateRequest;
import com.example.musinssak.api.order.dto.OrderCreateResponse;
import com.example.musinssak.api.order.dto.OrderItemsResponse;
import com.example.musinssak.api.order.dto.OrderUpdateRequest;
import com.example.musinssak.api.order.dto.OrderUpdateResponse;
import com.example.musinssak.application.order.OrderFacade;
import com.example.musinssak.application.order.OrderPrepareService;
import com.example.musinssak.application.order.OrderQueryService;
import com.example.musinssak.application.order.command.CreateOrderCommand;
import com.example.musinssak.application.order.result.CreateOrderResult;
import com.example.musinssak.common.web.ApiResponse; // 공통 응답 래퍼임
import io.swagger.v3.oas.annotations.Operation; // Swagger Operation
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * 주문 컨트롤러임
 * - 요청/응답만 처리함
 * - 비즈니스는 파사드/서비스가 함
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;                 // 주문 생성 파사드
    private final OrderQueryService orderQueryService;     // 주문 조회 서비스
    private final OrderPrepareService orderPrepareService; // 주문 준비(정보 갱신/재계산) 서비스

    /**
     * [POST] /api/orders/create
     * - 장바구니 선택 상품으로 주문 생성함
     * - 재고예약/만료시간 등은 파사드가 처리함
     */
    @Operation(summary = "주문 생성", description = "장바구니 선택 상품으로 주문 생성함")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공") // 이름 충돌 방지 위해 FQN 사용
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
                .orderPk(result.getOrderPk()) //주문 id pk
                .orderId(result.getOrderNo())                         // 주문번호임
                .reservationExpiresAt(result.getReservationExpires()) // 만료시각임
                .totalProductAmount(result.getTotalProductAmount())   // 총 원가 합임
                .discountAmount(result.getDiscountAmount())           // 총 할인 합임
                .deliveryFee(result.getDeliveryFee())                 // 배송비임
                .finalAmount(result.getFinalAmount())                 // 최종금액임
                .orderItems(
                        result.getItems() == null ? null :
                                result.getItems().stream()
                                        .map(it -> OrderCreateResponse.Item.builder()
                                                .productId(it.getProductId())
                                                .productName(it.getProductName())
                                                .brandName(it.getBrandName())
                                                .size(it.getSize())
                                                .quantity(it.getQuantity())
                                                .originalPrice(it.getOriginalPrice())
                                                .salePrice(it.getSalePrice())
                                                .build())
                                        .toList()
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
        OrderItemsResponse body = orderQueryService.getOrderItems(orderId, userId); // 서비스 호출함
        return ApiResponse.success(body); // 공통 포맷으로 감싸서 반환함
    }

    /**
     * [POST] /api/orders/{orderId}/prepare
     * - 주문 페이지에서 입력한 주문자/배송지 정보를 임시 저장함
     * - 현재 상품 가격 기준으로 금액을 즉시 재계산해서 돌려줌
     * - 주문이 CREATED 상태/유효시간 내일 때만 동작함
     */
    @Operation(summary = "주문 준비(정보 갱신 + 금액 재계산)",
            description = "주문자/배송지 정보를 임시 저장하고 현재 가격 기준으로 결제 예정 금액을 재계산함")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/{orderId}/prepare")
    public ApiResponse<OrderUpdateResponse> prepareOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderUpdateRequest req,
            Authentication authentication
    ) {
        Long userId = getUserIdOrThrow(authentication);
        OrderUpdateResponse body = orderPrepareService.updateAndRecalculate(orderId, userId, req);
        return ApiResponse.success(body);
    }

    /**
     * (선택) [PUT] /api/orders/{orderId}
     * - 위의 /prepare와 동일 동작을 제공함(호환용)
     * - 필요 없으면 나중에 제거해도 됨
     */
    @Operation(summary = "주문 정보 업데이트(호환용)", description = "prepare와 동일한 동작을 PUT으로 제공함")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/{orderId}")
    public ApiResponse<OrderUpdateResponse> updateOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderUpdateRequest req,
            Authentication authentication
    ) {
        Long userId = getUserIdOrThrow(authentication);
        OrderUpdateResponse body = orderPrepareService.updateAndRecalculate(orderId, userId, req);
        return ApiResponse.success(body);
    }

    /** 인증에서 userId 꺼냄 */
    private Long getUserIdOrThrow(Authentication authentication) {
        try {
            return Long.parseLong(authentication.getName()); // 문자열을 숫자로 바꿈
        } catch (Exception e) {
            throw new IllegalStateException("인증에서 userId를 찾을 수 없음");
        }
    }
}
