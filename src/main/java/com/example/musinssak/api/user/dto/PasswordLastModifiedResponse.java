package com.example.musinssak.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordLastModifiedResponse {
    private String lastModifiedDate; // e.g. "2024-06-15"
}