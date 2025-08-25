package com.example.musinssak.api.user;

import com.example.musinssak.api.user.dto.AddressResponse;
import com.example.musinssak.api.user.dto.CreateAddressRequest;
import com.example.musinssak.api.user.dto.DefaultAddressUpdateResponse;
import com.example.musinssak.api.user.dto.UpdateAddressRequest;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.domain.user.service.AddressService;
import com.example.musinssak.infra.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 배송지 목록 조회
     * GET /api/users/me/addresses
     * @param authorizationHeader
     * @return 로그인 사용자의 배송지 목록, 없으면 data: []
     */
    @GetMapping("/me/addresses")
    public ApiResponse<List<AddressResponse>> getMyAddresses(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long userId = Long.parseLong(jwtTokenProvider.getSubject(token));

        List<AddressResponse> data = addressService.getMyAddresses(userId);
        return ApiResponse.success("배송지 목록이 성공적으로 조회되었습니다.", data);
    }

    /**
     * 기본 배송지 설정
     * PUT /api/users/me/addresses/{addressId}/default
     * @param authorizationHeader
     * @param addressId
     * @return
     */
    @PutMapping("/me/addresses/{addressId}/default")
    public ApiResponse<DefaultAddressUpdateResponse> setDefaultAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long addressId
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long userId = Long.parseLong(jwtTokenProvider.getSubject(token));

        DefaultAddressUpdateResponse data = addressService.setDefaultAddress(userId, addressId);
        return ApiResponse.success("기본 배송지가 성공적으로 설정되었습니다.", data);
    }

    /**
     * 배송지 추가
     * POST /api/users/me/addresses
     * - isDefault=true면 기존 기본 해제 후 신규를 기본으로 설정
     * - 첫 배송지는 자동 기본
     * - 사용자당 최대 5개 제한
     * @param authorizationHeader Authorization: Bearer {accessToken}
     * @param request 배송지 생성 요청 본문
     * @return 201 CREATED + SUCCESS + 메시지 (목록은 프론트에서 재조회)
     */
    @PostMapping("/me/addresses")
    public ResponseEntity<ApiResponse<Void>> createAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody CreateAddressRequest request
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long userId = Long.parseLong(jwtTokenProvider.getSubject(token));

        addressService.createAddress(userId, request);

        return ResponseEntity.status(201)
                .body(ApiResponse.created("배송지가 성공적으로 등록되었습니다."));
    }

    /**
     * 배송지 수정
     * PUT /api/users/me/addresses/{addressId}
     * @param authorizationHeader
     * @param addressId
     * @param request
     * @return 목록은 프론트에서 재조회
     */
    @PutMapping("/me/addresses/{addressId}")
    public ApiResponse<Void> updateAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long addressId,
            @Valid @RequestBody UpdateAddressRequest request
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long userId = Long.parseLong(jwtTokenProvider.getSubject(token));

        addressService.updateAddress(userId, addressId, request);
        return ApiResponse.success("배송지가 성공적으로 수정되었습니다.");
    }

    /**
     * 배송지 삭제
     * DELETE /api/users/me/addresses/{addressId}
     * - 권한 체크: 내 주소만 삭제 가능 (아니면 403)
     * - 삭제 후 정책:
     *   (1) 삭제 전 총 개수가 2였으면 → 1개만 남으므로 남은 주소를 "자동 기본"으로 설정
     *   (2) 그 외(삭제 후 2개 이상 또는 0개)가 되면 → 기본 배송지 없을 수도 있음(허용)
     * - 성공 시: 200 + SUCCESS + 메시지 (목록은 프론트에서 재조회)
     * @param authorizationHeader
     * @param addressId
     * @return 목록은 프론트에서 재조회
     */
    @DeleteMapping("/me/addresses/{addressId}")
    public ApiResponse<Void> deleteAddress(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long addressId
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long userId = Long.parseLong(jwtTokenProvider.getSubject(token));

        addressService.deleteAddress(userId, addressId);
        return ApiResponse.success("배송지가 성공적으로 삭제되었습니다.");
    }

}
