package com.example.musinssak.domain.user.service;

import com.example.musinssak.api.user.dto.AddressResponse;
import com.example.musinssak.api.user.dto.CreateAddressRequest;
import com.example.musinssak.api.user.dto.DefaultAddressUpdateResponse;
import com.example.musinssak.api.user.dto.UpdateAddressRequest;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getMyAddresses(Long userId);

    DefaultAddressUpdateResponse setDefaultAddress(Long userId, Long addressId);

    void createAddress(Long userId, CreateAddressRequest request);

    void updateAddress(Long userId, Long addressId, UpdateAddressRequest request);

    void deleteAddress(Long userId, Long addressId);
}
