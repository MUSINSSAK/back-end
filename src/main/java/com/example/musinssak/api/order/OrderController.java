package com.example.musinssak.api.order;

import com.example.musinssak.api.order.dto.*;
import com.example.musinssak.application.order.OrderFacade;
import com.example.musinssak.application.order.OrderPrepareService;
import com.example.musinssak.application.order.OrderQueryService;
import com.example.musinssak.application.order.command.CreateOrderCommand;
import com.example.musinssak.application.order.result.CreateOrderResult;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.common.web.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;
    private final OrderQueryService orderQueryService;
    private final OrderPrepareService orderPrepareService;

    @Operation(summary = "주문 생성", description = "장바구니 선택 상품으로 주문 생성함")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/create")
    public ApiResponse<OrderCreateResponse> createOrder(
            @Valid @RequestBody OrderCreateRequest req,
            Authentication authentication
    ) {
        Long userId = getUserIdOrThrow(authentication);

        CreateOrderCommand command = CreateOrderCommand.builder()
                .userId(userId)
                .cartItemIds(req.getCartItemIds())
                .build();

        CreateOrderResult result = orderFacade.create(command);

        OrderCreateResponse resp = OrderCreateResponse.builder()
                .orderPk(result.getOrderPk())
                .orderId(result.getOrderNo())
                .reservationExpiresAt(result.getReservationExpires())
                .totalProductAmount(result.getTotalProductAmount())
                .discountAmount(result.getDiscountAmount())
                .deliveryFee(result.getDeliveryFee())
                .finalAmount(result.getFinalAmount())
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

        return ApiResponse.success(resp);
    }

    @Operation(summary = "주문 상품 조회", description = "주문 ID로 상품 목록과 합계를 내려줌(내 주문만 가능함)")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/{orderId}/items")
    public ApiResponse<OrderItemsResponse> getOrderItems(
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        Long userId = getUserIdOrThrow(authentication);
        OrderItemsResponse body = orderQueryService.getOrderItems(orderId, userId);
        return ApiResponse.success(body);
    }

    @Operation(summary = "주문번호로 주문 상품 조회", description = "주문번호로 상품 목록과 합계를 내려줌(내 주문만 가능함)")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/number/{orderNumber}/items")
    public ApiResponse<OrderItemsResponse> getOrderItemsByNumber(
            @PathVariable String orderNumber,
            Authentication authentication
    ) {
        Long userId = getUserIdOrThrow(authentication);
        OrderItemsResponse body = orderQueryService.getOrderItemsByNumber(orderNumber, userId);
        return ApiResponse.success(body);
    }

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

    @Operation(summary = "내 주문 내역 조회", description = "기간/상태별 필터링, 페이지네이션을 지원합니다.")
    @GetMapping("/me")
    public ApiResponse<OrderHistoryResponse> getMyOrderHistory(
            // [수정] @AuthenticationPrincipal 대신 다른 메소드와 동일하게 Authentication 객체를 받습니다.
            Authentication authentication,
            @RequestParam(defaultValue = "3months") String period,
            @RequestParam(defaultValue = "ALL") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // [수정] getUserIdOrThrow 헬퍼 메소드를 사용하여 userId를 가져옵니다.
        Long userId = getUserIdOrThrow(authentication);
        OrderHistoryResponse body = orderQueryService.getMyOrderHistory(userId, period, status, page, size);
        return ApiResponse.success("주문 내역이 성공적으로 조회되었습니다.", body);
    }

    /** 인증에서 userId 꺼냄 */
    private Long getUserIdOrThrow(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new BusinessException(ErrorCode.AUTH_REQUIRED);
        }
        return Long.parseLong(authentication.getPrincipal().toString());
    }
}