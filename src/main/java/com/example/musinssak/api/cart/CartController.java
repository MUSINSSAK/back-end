// src/main/java/com/example/musinssak/api/cart/CartController.java
package com.example.musinssak.api.cart;

import com.example.musinssak.api.cart.dto.CartAddItemRequest;
import com.example.musinssak.api.cart.dto.CartChangeQuantityRequest;
import com.example.musinssak.api.cart.dto.CartChangeQuantityResponse;
import com.example.musinssak.api.cart.dto.CartDeleteResponse;
import com.example.musinssak.api.cart.dto.CartDeleteSelectedRequest;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.cart.service.CartDeleteResult;
import com.example.musinssak.domain.cart.service.CartQuantityResult;
import com.example.musinssak.domain.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.musinssak.api.cart.dto.CartSelectRequest;
import com.example.musinssak.api.cart.dto.CartSelectResponse;


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
        Long userId = getUserIdOrThrow(auth);  // 인증에서 userId 꺼냄
        cartService.addItem(userId, req.getProductOptionId(), req.getQuantity()); // 담기 수행함
        return ApiResponse.success("장바구니에 담았음.");
    }

    /** 장바구니 수량 변경 */
    @Operation(summary = "장바구니 수량 변경")
    @PutMapping("/{cartItemId}/quantity")
    public ApiResponse<CartChangeQuantityResponse> changeQuantity(
            Authentication auth,
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartChangeQuantityRequest req
    ) {
        Long userId = getUserIdOrThrow(auth); // 인증 확인함

        CartQuantityResult r = cartService.changeQuantity(userId, cartItemId, req.getQuantity()); // 수량 변경함

        // 응답으로 바꿔서 내려줌
        CartChangeQuantityResponse body = new CartChangeQuantityResponse(
                r.getCartItemId(),
                r.getOldQuantity(),
                r.getNewQuantity(),
                r.getSalePrice() * r.getNewQuantity(),
                r.getAvailableStock(),
                r.getNewQuantity() < r.getAvailableStock(),
                r.getNewQuantity() > 1
        );
        return ApiResponse.success("수량이 변경되었음.", body);
    }

    /** X 버튼 단건 삭제: DELETE /api/cart/items/{cartItemId} */
    @Operation(summary = "장바구니 단건 삭제 (X 버튼)")
    @DeleteMapping("/items/{cartItemId}")
    public ApiResponse<CartDeleteResponse> removeItem(
            Authentication auth,
            @PathVariable Long cartItemId
    ) {
        Long userId = getUserIdOrThrow(auth);
        CartDeleteResult result = cartService.removeItem(userId, cartItemId);
        return ApiResponse.success("상품이 삭제되었음.",
                new CartDeleteResponse(result.getDeletedCount(), result.getRemainingItems()));
    }

    /** 체크박스 선택 삭제: DELETE /api/cart/items  (body: cartItemIds) */
    @Operation(summary = "장바구니 선택 삭제 (체크박스)")
    @DeleteMapping("/items")
    public ApiResponse<CartDeleteResponse> removeSelected(
            Authentication auth,
            @RequestBody @Valid CartDeleteSelectedRequest req
    ) {
        Long userId = getUserIdOrThrow(auth);
        CartDeleteResult result = cartService.removeSelected(userId, req.getCartItemIds());
        return ApiResponse.success("선택한 상품들이 삭제되었음.",
                new CartDeleteResponse(result.getDeletedCount(), result.getRemainingItems()));
    }

    /** 장바구니 선택/해제 (단일/복수/전체) */
    @Operation(summary = "장바구니 선택/해제 (단일/복수/전체)")
    @PutMapping("/select")
    public ApiResponse<CartSelectResponse> setSelected(
            Authentication auth,
            @RequestBody @Valid CartSelectRequest req
    ) {
        Long userId = getUserIdOrThrow(auth);

        if (req.getIsSelected() == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST); // isSelected는 필수 의미
        }

        CartSelectResponse result;
        if (Boolean.TRUE.equals(req.getSelectAll())) {
            // 전체 선택/해제
            result = cartService.setSelectedAll(userId, req.getIsSelected());
        } else {
            // 단일/복수 선택/해제
            if (req.getCartItemIds() == null || req.getCartItemIds().isEmpty()) {
                throw new BusinessException(ErrorCode.INVALID_REQUEST);
            }
            result = cartService.setSelectedBulk(userId, req.getCartItemIds(), req.getIsSelected());
        }

        return ApiResponse.success("상품 선택이 변경되었음.", result);
    }



    /** 인증 객체에서 userId를 꺼내는 도우미임 */
    private Long getUserIdOrThrow(Authentication auth) {
        if (auth == null || !auth.isAuthenticated())
            throw new BusinessException(ErrorCode.AUTH_REQUIRED); // 로그인 안했으면 거절됨
        try {
            return Long.parseLong(auth.getName()); // 이름 칸에 userId를 넣어놨다고 가정함
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.AUTH_REQUIRED); // 이상하면 거절됨
        }
    }
}
