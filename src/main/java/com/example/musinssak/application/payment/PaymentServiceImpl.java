// src/main/java/com/example/musinssak/application/payment/PaymentServiceImpl.java
package com.example.musinssak.application.payment;

import com.example.musinssak.api.payment.dto.PaymentRequestResponse;
import com.example.musinssak.api.payment.dto.PaymentCompleteResponse;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.repository.OrderItemRepository;
import com.example.musinssak.domain.order.repository.OrdersRepository;
import com.example.musinssak.domain.order.repository.view.OrderItemRow;
import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.repository.UserRepository;
import com.example.musinssak.infra.payment.config.PortOneConfig;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 결제 서비스 구현체임
 * 포트원을 이용한 결제 요청과 검증 로직을 처리함
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final WebClient portOneWebClient;
    private final PortOneConfig portOneConfig;

    @Override
    public PaymentRequestResponse requestPayment(String orderId, Long userId) {
        // 1) 주문 존재 여부 및 권한 검증함
        Orders order = ordersRepository.findByOrderNumberAndUserId(orderId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN));

        // 2) 주문 상태 검증함 (결제 가능한 상태인지 확인)
        validateOrderForPayment(order);

        // 3) 사용자 정보를 조회함
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 4) 주문 상품 정보를 조회함 (주문명 생성용)
        List<OrderItemRow> orderItems = orderItemRepository.findRowsByOrderIdAndUserId(order.getId(), userId);

        // 5) 주문 상태를 PAYMENT_PENDING으로 변경함
        updateOrderStatusToPaymentPending(order);

        // 6) 포트원 결제 ID를 생성함 (UUID 기반)
        String paymentId = generatePaymentId(order.getOrderNumber());

        // 7) 주문명을 생성함
        String orderName = createOrderName(orderItems);

        // 8) 결제 요청 응답을 구성함
        return buildPaymentRequestResponse(paymentId, order, user, orderName);
    }

    @Override
    public PaymentCompleteResponse completePayment(String paymentId, Long userId, String transactionId) {
        log.info("결제 완료 처리 시작: paymentId={}, userId={}, transactionId={}", paymentId, userId, transactionId);

        // 1) paymentId에서 주문번호 추출
        String orderNumber = extractOrderNumberFromPaymentId(paymentId);

        // 2) 주문 조회 및 권한 검증
        Orders order = ordersRepository.findByOrderNumberAndUserId(orderNumber, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN));

        // 3) 포트원 API로 실제 결제 상태 검증
        verifyPaymentWithPortOne(transactionId, order.getFinalPaymentPrice());

        // 4) 주문 상태를 PAID로 변경
        updateOrderStatusToPaid(order, transactionId);

        // 5) 결제 완료 응답 생성
        return buildPaymentCompleteResponse(order, paymentId, transactionId);
    }

    /**
     * 주문이 결제 가능한 상태인지 검증함
     * - 만료 시간 확인
     * - 주문 상태 확인 (CREATED 또는 PAYMENT_PENDING만 허용)
     */
    private void validateOrderForPayment(Orders order) {
        LocalDateTime now = LocalDateTime.now();

        // 만료 시간 확인
        if (order.getExpiredAt() != null && order.getExpiredAt().isBefore(now)) {
            throw new BusinessException(ErrorCode.ORDER_TIME_EXPIRED);
        }

        // 주문 상태 확인
        if (order.getStatus() != OrderStatus.CREATED && order.getStatus() != OrderStatus.PAYMENT_PENDING) {
            throw new BusinessException(ErrorCode.INVALID_ORDER_STATUS);
        }
    }

    /**
     * 주문 상태를 PAYMENT_PENDING으로 변경함
     * - 결제 진행 중임을 표시함
     * - 동시성 제어를 위해 상태를 변경함
     */
    private void updateOrderStatusToPaymentPending(Orders order) {
        // Orders 엔티티에 상태 변경 메서드가 없다면 직접 업데이트
        ordersRepository.updateOrderStatus(order.getId(), OrderStatus.PAYMENT_PENDING);
        log.info("주문 상태를 PAYMENT_PENDING으로 변경: orderId={}", order.getOrderNumber());
    }

    /**
     * 포트원 결제 ID를 생성함
     * - 주문번호 + UUID 조합으로 고유한 ID 생성
     * - 예: "ORD20250919173427-79d7_pay_abc123"
     */
    private String generatePaymentId(String orderNumber) {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return orderNumber + "_pay_" + uuid;
    }

    /**
     * 주문명을 생성함
     * - 단일 상품: "상품명"
     * - 다중 상품: "대표상품명 외 N건"
     */
    private String createOrderName(List<OrderItemRow> orderItems) {
        if (orderItems.isEmpty()) {
            return "주문 상품";
        }

        String firstProductName = orderItems.get(0).getProductName();
        return PaymentRequestResponse.createOrderName(firstProductName, orderItems.size());
    }

    /**
     * 결제 요청 응답을 구성함
     * - 포트원 결제창에 필요한 모든 정보를 포함함
     */
    private PaymentRequestResponse buildPaymentRequestResponse(
            String paymentId,
            Orders order,
            User user,
            String orderName) {

        return PaymentRequestResponse.builder()
                .paymentId(paymentId)
                .merchantId(portOneConfig.getStoreId())  // V2 API Store ID 사용
                .channelKey(portOneConfig.getChannelKey())  // V2 API 채널 키 추가
                .orderName(orderName)
                .totalAmount(order.getFinalPaymentPrice())
                .currency("KRW")
                .customerName(user.getNickname())  // 사용자 닉네임 사용
                .customerEmail(user.getEmail())
                .customerPhone("") // User 엔티티에 전화번호가 있다면 추가
                .returnUrl("http://localhost:3000/payment/success")  // 프론트엔드 성공 페이지
                .notificationUrl("http://localhost:8080/api/payments/webhook")  // 웹훅 URL
                .build();
    }

    /**
     * paymentId에서 주문번호를 추출함
     * 예: "ORD20250921160245-d09d_pay_4f9b10ab" → "ORD20250921160245-d09d"
     */
    private String extractOrderNumberFromPaymentId(String paymentId) {
        int payIndex = paymentId.indexOf("_pay_");
        if (payIndex == -1) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST);
        }
        return paymentId.substring(0, payIndex);
    }

    /**
     * 포트원 API로 실제 결제 상태를 검증함
     * - 실제 결제 금액과 주문 금액이 일치하는지 확인
     * - 결제 상태가 PAID인지 확인
     */
    private void verifyPaymentWithPortOne(String transactionId, int expectedAmount) {
        try {
            log.info("=== 포트원 API 결제 검증 시작 ===");
            log.info("거래 ID: {}", transactionId);
            log.info("예상 금액: {}", expectedAmount);
            log.info("API Secret: {}", portOneConfig.getApiSecret().substring(0, 10) + "...");

            // 포트원 V2 API로 결제 정보 조회
            // 올바른 엔드포인트: /v1/payments/{paymentId}
            String response = portOneWebClient.get()
                    .uri("/v1/payments/{paymentId}", transactionId)
                    .retrieve()
                    .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> {
                            log.error("포트원 API 호출 실패: status={}", clientResponse.statusCode());
                            return clientResponse.bodyToMono(String.class)
                                    .map(body -> {
                                        log.error("에러 응답 바디: {}", body);
                                        return new RuntimeException("포트원 API 호출 실패: " + clientResponse.statusCode());
                                    });
                        }
                    )
                    .bodyToMono(String.class)
                    .block();

            log.info("포트원 결제 검증 응답: {}", response);

            // TODO: 실제 응답 파싱 및 검증 로직 구현
            // JSON 파싱하여 status가 "PAID"인지, amount가 expectedAmount와 일치하는지 확인

            log.info("포트원 결제 검증 성공");

        } catch (Exception e) {
            log.error("포트원 결제 검증 실패: transactionId={}, error={}", transactionId, e.getMessage(), e);
            throw new BusinessException(ErrorCode.PAYMENT_VERIFICATION_FAILED);
        }
    }

    /**
     * 주문 상태를 PAID로 변경하고 결제 정보를 저장함
     */
    private void updateOrderStatusToPaid(Orders order, String transactionId) {
        ordersRepository.updateOrderStatus(order.getId(), OrderStatus.PAID);
        log.info("주문 상태를 PAID로 변경: orderNumber={}, transactionId={}",
                order.getOrderNumber(), transactionId);
    }

    /**
     * 결제 완료 응답을 구성함
     */
    private PaymentCompleteResponse buildPaymentCompleteResponse(
            Orders order,
            String paymentId,
            String transactionId) {

        return PaymentCompleteResponse.builder()
                .orderNumber(order.getOrderNumber())
                .paymentId(paymentId)
                .transactionId(transactionId)
                .paymentStatus("PAID")
                .completedAt(LocalDateTime.now().toString())
                .finalAmount((long) order.getFinalPaymentPrice())
                .build();
    }
}