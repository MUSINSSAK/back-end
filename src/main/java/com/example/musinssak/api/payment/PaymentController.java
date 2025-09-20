
package com.example.musinssak.api.payment;

import com.example.musinssak.api.payment.dto.PaymentInfoResponse;
import com.example.musinssak.application.payment.PaymentQueryService;
import com.example.musinssak.common.web.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * 결제 컨트롤러임
 * - 결제 관련 API를 처리함
 * - 요청/응답만 처리하고 비즈니스 로직은 서비스에 위임함
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentQueryService paymentQueryService;

    /**
     * [GET] /api/payments/{orderId}/info
     * - 결제 페이지에서 주문 상품과 최종 결제 정보를 조회함
     * - 30분 시간 제한이 있으며, 10분 이하 남았을 때 알람을 표시함
     * - 만료된 주문의 경우 ORDER_TIME_EXPIRED 예외를 발생시킴
     *
     * @param orderId 주문번호 (예: ORD20250726001)
     * @param authentication 인증 정보 (사용자 ID 추출용)
     * @return ApiResponse<PaymentInfoResponse> 결제 정보 응답
     */
    @Operation(
            summary = "결제 정보 조회",
            description = "결제 페이지에서 주문 상품과 최종 결제 정보를 조회합니다. 30분 시간 제한이 있으며, 10분 이하일 때 알람을 표시합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "결제 정보 조회가 완료되었습니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "로그인이 필요합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "주문을 찾을 수 없습니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "주문 시간이 만료되었습니다.")
    @GetMapping("/{orderId}/info")
    public ApiResponse<PaymentInfoResponse> getPaymentInfo(
            @PathVariable String orderId,
            Authentication authentication
    ) {
        // 1) 인증에서 사용자 ID를 추출함
        Long userId = getUserIdOrThrow(authentication);

        // 2) 결제 정보 조회 서비스를 호출함
        PaymentInfoResponse paymentInfo = paymentQueryService.getPaymentInfo(orderId, userId);

        // 3) 공통 응답 형태로 감싸서 반환함
        return ApiResponse.success("결제 정보 조회가 완료되었습니다.", paymentInfo);
    }

    /**
     * 인증 정보에서 사용자 ID를 추출함
     * - Spring Security Authentication에서 사용자 ID를 가져옴
     * - 인증 실패 시 예외를 발생시킴
     *
     * @param authentication Spring Security 인증 객체
     * @return Long 사용자 ID
     * @throws IllegalStateException 인증 정보가 올바르지 않을 때
     */
    private Long getUserIdOrThrow(Authentication authentication) {
        try {
            return Long.parseLong(authentication.getName()); // 문자열을 숫자로 변환함
        } catch (Exception e) {
            throw new IllegalStateException("인증에서 userId를 찾을 수 없음");
        }
    }
}