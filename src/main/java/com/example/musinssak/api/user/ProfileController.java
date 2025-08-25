package com.example.musinssak.api.user;

import com.example.musinssak.api.user.dto.UpdatePasswordRequest;
import com.example.musinssak.api.user.dto.UpdateUserProfileRequest;
import com.example.musinssak.api.user.dto.UserProfileResponse;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.user.entity.User;
import com.example.musinssak.domain.user.service.UserService;
import com.example.musinssak.infra.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 프로필 조회
     * GET /api/users/me/profile
     * @param authorizationHeader
     * @return 프로필 정보 반환
     */
    @GetMapping("/me/profile")
    public ApiResponse<UserProfileResponse> getMyProfile(
            @RequestHeader("Authorization") String authorizationHeader) {
        // 1) Authorization: Bearer <token> 에서 토큰만 추출
        String token = authorizationHeader.replace("Bearer ", "").trim();
        // 2) 토큰에서 userId(subject) 추출
        String userId = jwtTokenProvider.getSubject(token);

        // 3) 사용자 조회
        User user = userService.getUserById(Long.parseLong(userId));

        // 4) 응답 DTO로 변환해 반환
        return ApiResponse.success("프로필 정보가 성공적으로 조회되었습니다.",
                new UserProfileResponse(user));
    }

    /**
     * 프로필 수정(이름, 휴대폰, 생년월일, 프로필 이미지)
     * PUT /api/users/me/profile
     *
     * - 이메일은 수정 불가
     * - 일부 값만 보내도 됨(null은 "변경 안 함"으로 처리)
     */
    @PutMapping("/me/profile")
    public ApiResponse<UserProfileResponse> updateMyProfile(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdateUserProfileRequest request
    ) {
        // 1) 토큰 → userId 추출
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String userId = jwtTokenProvider.getSubject(token);

        // 2) 서비스에 수정 위임 (트랜잭션 내에서 엔티티 변경)
        User updated = userService.updateProfile(Long.parseLong(userId), request);

        // 3) 수정된 최신 정보 그대로 반환
        return ApiResponse.success("프로필 정보가 성공적으로 수정되었습니다.",
                new UserProfileResponse(updated));
    }

    /**
     * 비밀번호 변경
     * PUT /api/users/me/password
     * @param authorizationHeader - 요청 헤더에서 액세스 토큰을 추출
     * @param request - 현재 비밀번호와 새 비밀번호
     * @return - 비밀번호 변경 성공 또는 실패 메시지
     */
    @PutMapping("/me/password")
    public ResponseEntity<ApiResponse<?>> changePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UpdatePasswordRequest request) {

        String token = authorizationHeader.replace("Bearer ", "").trim();
        String userId = jwtTokenProvider.getSubject(token);  // 액세스 토큰에서 사용자 ID 추출

        // 비밀번호 변경 후 마지막 변경일 반환
        String lastModifiedDate = userService.updatePassword(userId, request);

        return ResponseEntity.ok(ApiResponse.success("비밀번호가 성공적으로 변경되었습니다.", lastModifiedDate));
    }
}
