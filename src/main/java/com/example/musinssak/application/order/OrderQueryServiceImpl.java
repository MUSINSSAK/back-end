package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.*;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.repository.OrderItemRepository;
import com.example.musinssak.domain.order.repository.OrdersRepository;
import com.example.musinssak.domain.order.repository.view.OrderItemRow;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/** 주문 조회 구현체임 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderStatusUpdater orderStatusUpdater;

    @Override
    public OrderItemsResponse getOrderItems(Long orderId, Long userId) {
        // ... (이 메소드는 수정 없음)
        Orders order = ordersRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN));

        if (order.getStatus() == OrderStatus.CREATED
                && order.getExpiredAt() != null
                && order.getExpiredAt().isBefore(LocalDateTime.now())) {
            orderStatusUpdater.markPaymentExpiredNow(order.getId());
            throw new BusinessException(ErrorCode.ORDER_TIME_EXPIRED);
        }

        List<OrderItemRow> rows = orderItemRepository.findRowsByOrderIdAndUserId(orderId, userId);

        return OrderItemsResponse.builder()
                .orderPk(order.getId())
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .status(order.getStatus().name())
                .reservationExpiresAt(order.getExpiredAt())
                .totalProductAmount(order.getTotalProductPrice())
                .discountAmount(order.getTotalDiscountPrice())
                .deliveryFee(order.getDeliveryFee())
                .finalAmount(order.getFinalPaymentPrice())
                .items(rows.stream().map(r ->
                        OrderItemsResponse.Item.builder()
                                .productId(r.getProductId())
                                .productName(r.getProductName())
                                .brandName(r.getBrandName())
                                .size(r.getSize())
                                .quantity(r.getQuantity())
                                .originalPrice(r.getOriginalPrice())
                                .salePrice(r.getSalePrice())
                                .imageUrl(r.getImageUrl())
                                .build()
                ).toList())
                .build();
    }

    @Override
    public OrderItemsResponse getOrderItemsByNumber(String orderNumber, Long userId) {
        // ... (이 메소드는 수정 없음)
        Orders order = ordersRepository.findByOrderNumberAndUserId(orderNumber, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN));

        if (order.getStatus() == OrderStatus.CREATED
                && order.getExpiredAt() != null
                && order.getExpiredAt().isBefore(LocalDateTime.now())) {
            orderStatusUpdater.markPaymentExpiredNow(order.getId());
            throw new BusinessException(ErrorCode.ORDER_TIME_EXPIRED);
        }

        List<OrderItemRow> rows = orderItemRepository.findRowsByOrderIdAndUserId(order.getId(), userId);

        return OrderItemsResponse.builder()
                .orderPk(order.getId())
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .status(order.getStatus().name())
                .reservationExpiresAt(order.getExpiredAt())
                .totalProductAmount(order.getTotalProductPrice())
                .discountAmount(order.getTotalDiscountPrice())
                .deliveryFee(order.getDeliveryFee())
                .finalAmount(order.getFinalPaymentPrice())
                .items(rows.stream().map(r ->
                        OrderItemsResponse.Item.builder()
                                .productId(r.getProductId())
                                .productName(r.getProductName())
                                .brandName(r.getBrandName())
                                .size(r.getSize())
                                .quantity(r.getQuantity())
                                .originalPrice(r.getOriginalPrice())
                                .salePrice(r.getSalePrice())
                                .imageUrl(r.getImageUrl())
                                .build()
                ).toList())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderHistoryResponse getMyOrderHistory(Long userId, String period, String status, int page, int size) {
        LocalDateTime startDate = switch (period) {
            case "1month" -> LocalDateTime.now().minusMonths(1);
            case "3months" -> LocalDateTime.now().minusMonths(3);
            case "6months" -> LocalDateTime.now().minusMonths(6);
            default -> null;
        };

        // -- [이 switch 구문을 수정했습니다] --
        List<OrderStatus> statuses = switch (status) {
            // 'ORDERED'는 현재 'PAID' 상태만 보도록 단순화했습니다.
            case "ORDERED" -> List.of(OrderStatus.PAID);
            // 'CANCELLED'는 enum에 존재하므로 그대로 둡니다.
            case "CANCELLED" -> List.of(OrderStatus.CANCELLED);
            // 'RETURNED'는 enum에 없으므로, 빈 목록을 반환하도록 처리합니다.
            case "RETURNED" -> List.of();
            // 'ALL' 또는 그 외의 경우는 모든 상태를 조회하므로 빈 목록을 전달합니다.
            default -> List.of();
        };

        Pageable pageable = PageRequest.of(page, size);
        Page<Orders> orderPage = ordersRepository.findMyOrderHistory(userId, startDate, statuses, pageable);

        List<OrderHistoryItemDto> orderDtos = orderPage.getContent().stream()
                .map(order -> {
                    List<OrderItemDto> itemDtos = order.getItems().stream()
                            .map(item -> OrderItemDto.builder()
                                    .name(item.getProduct().getName())
                                    .option(item.getProductOptionId() + " / 수량: " + item.getQuantity() + "개")
                                    .thumbnailUrl(item.getProduct().getThumbnailImageUrl())
                                    .build())
                            .collect(Collectors.toList());

                    return OrderHistoryItemDto.builder()
                            .orderDate(order.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                            .orderNumber(order.getOrderNumber())
                            .items(itemDtos)
                            .totalAmount(order.getFinalPaymentPrice())
                            .orderStatus(order.getStatus())
                            .build();
                })
                .collect(Collectors.toList());

        PaginationDto paginationDto = PaginationDto.builder()
                .page(orderPage.getNumber())
                .size(orderPage.getSize())
                .totalElements(orderPage.getTotalElements())
                .totalPages(orderPage.getTotalPages())
                .build();

        return new OrderHistoryResponse(orderDtos, paginationDto);
    }
}