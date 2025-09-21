package com.example.musinssak.api.chat;

import com.example.musinssak.api.chat.dto.ChatRequest;
import com.example.musinssak.api.chat.dto.ChatResponse;
import com.example.musinssak.domain.chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.security.core.annotation.AuthenticationPrincipal; // 실제로는 인증된 사용자 정보 사용

@RestController
@RequestMapping("/api/users/me/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> getChatbotResponse(
            // @AuthenticationPrincipal UserDetails userDetails, // 실제로는 이런 방식으로 사용자 ID를 가져옵니다.
            @RequestBody ChatRequest requestDto
    ) {
        // String userId = userDetails.getUsername(); // 사용자 ID
        String userId = "user123"; // 임시 사용자 ID
        ChatResponse response = chatService.getChatbotResponse(requestDto.query(), userId);
        return ResponseEntity.ok(response);
    }
}