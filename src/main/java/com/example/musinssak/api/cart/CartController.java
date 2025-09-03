// src/main/java/com/example/musinssak/api/cart/CartController.java
package com.example.musinssak.api.cart;

import com.example.musinssak.api.cart.dto.CartAddItemRequest;
import com.example.musinssak.api.cart.dto.CartChangeQuantityRequest;
import com.example.musinssak.api.cart.dto.CartChangeQuantityResponse;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.cart.service.CartQuantityResult;
import com.example.musinssak.domain.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart", description = "장바구니 API")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /** 장바구니 담기 */
    @Operation(summary = "장바구니 담기")
    @PostMapping
    public ApiResponse<Void> addItem(Authentication auth, @RequestBody @Valid CartAddItemRequest req) {
        Long userId = getUserIdOrThrow(auth); // 인증 파싱함
        cartService.addItem(userId, req.getProductOptionId(), req.getQuantity()); // 서비스 호출함
        return ApiResponse.success("장바구니에 담았습니다."); // 성공 응답 내려줌
    }

    /** 장바구니 수량 변경 */
    @Operation(summary = "장바구니 수량 변경")
    @PutMapping("/{cartItemId}/quantity")
    public ApiResponse<CartChangeQuantityResponse> changeQuantity(
            Authentication auth,
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartChangeQuantityRequest req
    ) {
        Long userId = getUserIdOrThrow(auth); // 인증 파싱함

        CartQuantityResult r = cartService.changeQuantity(userId, cartItemId, req.getQuantity()); // 서비스 호출함

        // 응답 변환함
        CartChangeQuantityResponse body = new CartChangeQuantityResponse(
                r.getCartItemId(),
                r.getOldQuantity(),
                r.getNewQuantity(),
                r.getSalePrice() * r.getNewQuantity(),
                r.getAvailableStock(),
                r.getNewQuantity() < r.getAvailableStock(),
                r.getNewQuantity() > 1
        );
        return ApiResponse.success("수량이 변경되었습니다.", body);
    }

    /** 인증 객체에서 userId 파싱함 */
    private Long getUserIdOrThrow(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) throw new BusinessException(ErrorCode.AUTH_REQUIRED);
        try { return Long.parseLong(auth.getName()); } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.AUTH_REQUIRED);
        }
    }
}
