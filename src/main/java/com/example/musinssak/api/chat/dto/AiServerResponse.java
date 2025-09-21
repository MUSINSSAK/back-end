package com.example.musinssak.api.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record AiServerResponse(
        String answer,

        @JsonProperty("recommended_ids") // snake_case와 cammelCase 호환
        List<String> recommendedIds
) {}