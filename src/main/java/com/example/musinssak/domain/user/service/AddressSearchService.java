package com.example.musinssak.domain.user.service;

import com.example.musinssak.api.user.dto.AddressSearchResponse;

public interface AddressSearchService {
    AddressSearchResponse search(String query);
}
