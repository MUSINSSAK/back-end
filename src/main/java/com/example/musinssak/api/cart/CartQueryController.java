// src/main/java/com/example/musinssak/api/cart/CartQueryController.java
package com.example.musinssak.api.cart;

import com.example.musinssak.api.cart.dto.CartGetResponse;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.cart.service.CartQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Cart", description = "장바구니 API")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartQueryController {

    private final CartQueryService cartQueryService;

    @Operation(summary = "장바구니 조회")
    @GetMapping
    public ApiResponse<CartGetResponse> getCart(Authentication auth) {
        // 인증 확인함
        if (auth == null || !auth.isAuthenticated()) throw new BusinessException(ErrorCode.AUTH_REQUIRED);

        // userId 파싱함
        Long userId;
        try { userId = Long.parseLong(auth.getName()); }
        catch (NumberFormatException e) { throw new BusinessException(ErrorCode.AUTH_REQUIRED); }

        // 조회 호출함
        CartGetResponse data = cartQueryService.getCart(userId);

        // 성공 응답 내려줌
        return ApiResponse.success("장바구니 조회가 완료되었습니다.", data);
    }
}
