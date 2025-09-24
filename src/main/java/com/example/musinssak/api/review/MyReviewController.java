package com.example.musinssak.api.review;

import com.example.musinssak.api.review.dto.MyReviewListResponse;
import com.example.musinssak.api.review.dto.ReviewCreateRequest;
import com.example.musinssak.api.review.dto.ReviewUpdateRequest;
import com.example.musinssak.api.review.facade.MyReviewFacade;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
// [1. 추가] SecurityContextHolder를 import 합니다.
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/me/reviews")
public class MyReviewController {

    private final MyReviewFacade myReviewFacade;
    private final UserRepository userRepository;

    private Long getLoginUserId() {
        // [2. 추가] SecurityContextHolder에서 Principal(인증 주체, 보통 userId)을 직접 가져오는 메소드
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            // 이 예외는 보통 GlobalExceptionHandler에서 401 Unauthorized로 처리됩니다.
            throw new IllegalStateException("인증된 사용자 정보를 찾을 수 없습니다.");
        }
        // Principal이 String으로 저장되어 있을 수 있으므로, toString() 후 Long으로 변환합니다.
        return Long.valueOf(principal.toString());
    }

    @GetMapping
    // [3. 수정] @AuthenticationPrincipal 어노테이션을 완전히 제거합니다.
    public ResponseEntity<ApiResponse<MyReviewListResponse>> getMyReviews() {
        // [4. 수정] 위에서 만든 메소드를 호출하여 userId를 가져옵니다.
        Long userId = getLoginUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));

        MyReviewListResponse response = myReviewFacade.getMyReviews(user);
        return ResponseEntity.ok(ApiResponse.success("상품 리뷰 목록 조회에 성공했습니다.", response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createReview(
            @RequestBody @Valid ReviewCreateRequest request) {
        Long userId = getLoginUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));
        myReviewFacade.createReview(user, request);
        return ResponseEntity.ok(ApiResponse.success("리뷰가 성공적으로 등록되었습니다."));
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> updateReview(
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewUpdateRequest request) {
        Long userId = getLoginUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));
        myReviewFacade.updateReview(user, reviewId, request);
        return ResponseEntity.ok(ApiResponse.success("리뷰가 성공적으로 수정되었습니다."));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(
            @PathVariable Long reviewId) {
        Long userId = getLoginUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다. ID: " + userId));
        myReviewFacade.deleteReview(user, reviewId);
        return ResponseEntity.ok(ApiResponse.success("리뷰가 성공적으로 삭제되었습니다."));
    }
}