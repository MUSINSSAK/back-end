package com.example.musinssak.api.chat;

import com.example.musinssak.api.chat.dto.ChatRequest;
import com.example.musinssak.api.chat.dto.ChatResponse;
import com.example.musinssak.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.security.core.annotation.AuthenticationPrincipal; // 실제로는 인증된 사용자 정보 사용

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/api/chat")
    public ResponseEntity<ChatResponse> getGenericChatResponse(
            @RequestBody ChatRequest requestDto
    ) {
        ChatResponse response = chatService.getChatbotResponse(requestDto.query());
        return ResponseEntity.ok(response);
    }
}