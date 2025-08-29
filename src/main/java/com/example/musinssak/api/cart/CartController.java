// src/main/java/com/example/musinssak/api/cart/CartController.java
package com.example.musinssak.api.cart;

import com.example.musinssak.api.cart.dto.CartAddItemRequest;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// "장바구니 API" 라는 이름표를 붙여요 (Swagger 문서에서 보기 좋게).
@Tag(name = "Cart", description = "장바구니 API")
// 여기는 REST 컨트롤러예요. 웹 요청을 받아서 답해줘요.
@RestController
// "/api/cart" 로 시작하는 주소를 여기가 맡아요.
@RequestMapping("/api/cart")
// 생성자 주입을 자동으로 만들어줘요. (final 필드 자동 주입)
@RequiredArgsConstructor
public class CartController {

    // 장바구니 일을 실제로 해주는 서비스(비서)예요.
    private final CartService cartService;

    // "장바구니에 물건 담기" 버튼 같은 일을 하는 곳이에요.
    @Operation(summary = "장바구니에 상품 담기(추가)")
    @PostMapping
    public ApiResponse<Void> addItem(
            // 로그인한 사람이 누구인지 알려주는 상자예요.
            Authentication auth,
            // 사용자가 보낸 JSON(상품옵션/수량)을 깔끔한 객체로 받아요.
            @RequestBody @Valid CartAddItemRequest req
    ) {
        // 1) 로그인 했는지 먼저 확인해요.
        //    로그인 안 했으면 에러를 던져서 "로그인 필요"라고 알려줘요.
        if (auth == null || !auth.isAuthenticated()) {
            throw new BusinessException(ErrorCode.AUTH_REQUIRED);
        }

        // 2) 로그인한 사용자의 id(문자열)를 숫자로 바꿔요.
        //    혹시 숫자가 아니면(이상하면) 역시 "로그인 필요"라고 해요.
        Long userId;
        try {
            userId = Long.parseLong(auth.getName()); // 예: "123" -> 123L
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.AUTH_REQUIRED);
        }

        // 3) 진짜 담는 일을 "서비스"에게 시켜요.
        //    !!! 중요: DTO는 롬복 Getter를 쓰니까 꼭 get...() 으로 꺼내요 !!!
        cartService.addItem(userId, req.getProductOptionId(), req.getQuantity());

        // 4) 잘 됐다면 "성공했어요!" 라는 200 응답을 보내줘요.
        return ApiResponse.success("장바구니에 담았습니다.");
    }
}
