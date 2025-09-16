package com.example.musinssak.api.point;

import com.example.musinssak.api.point.dto.PointHistoryListResponse;
import com.example.musinssak.api.point.facade.PointHistoryFacade;
import com.example.musinssak.common.util.PeriodFilter;
import com.example.musinssak.common.web.ApiResponse;
import com.example.musinssak.infra.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * (적립금) 사용자 적립금 내역 조회
 * GET /api/users/me/points/histories?period=1m|3m|6m|all
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class PointHistoryController {

    private final PointHistoryFacade pointHistoryFacade;  // 다음 단계에서 구현
    private final JwtTokenProvider jwtTokenProvider;      // 기존 방식 그대로 사용

    @GetMapping("/me/points/histories")
    public ApiResponse<PointHistoryListResponse> getMyPointHistories(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(name = "period", defaultValue = "all") String period
    ) {
        // 1) 토큰 → userId
        String token = authorizationHeader.replace("Bearer ", "").trim();
        Long userId = Long.parseLong(jwtTokenProvider.getSubject(token));

        // 2) 기간 파라미터 파싱 (공통 유틸)
        PeriodFilter filter = PeriodFilter.from(period);

        // 3) 파사드 위임 (서비스 조립/검증/조회는 파사드/서비스에서 처리)
        PointHistoryListResponse data = pointHistoryFacade.getPointHistories(userId, filter);

        // 4) 공통 응답 포맷
        return ApiResponse.success("적립금 내역이 성공적으로 조회되었습니다.", data);
    }
}
