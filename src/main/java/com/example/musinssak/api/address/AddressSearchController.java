package com.example.musinssak.api.address;

import com.example.musinssak.api.user.dto.AddressSearchResponse;
import com.example.musinssak.domain.user.service.AddressSearchService;
import com.example.musinssak.common.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressSearchController {

    private final AddressSearchService addressSearchService;

    /**
     * 주소 검색
     * GET /api/address/search?query=서울 강남구
     */
    @GetMapping("/search")
    public ApiResponse<AddressSearchResponse> search(@RequestParam String query) {
        var data = addressSearchService.search(query);
        return ApiResponse.success("주소 검색이 완료되었습니다.", data);
    }
}
