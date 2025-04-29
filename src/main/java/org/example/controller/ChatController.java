package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Message;
import org.example.service.AIService;
import org.example.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final AIService aiService;
    private final MessageService messageService;

    @PostMapping("/{sessionId}")
    public ResponseEntity<Message> handleChatMessage(
            @PathVariable Long sessionId,
            @RequestBody Map<String, String> request
    ) {
        String userMessage = request.get("message");

        Message userMsg = messageService.createUserMessage(userMessage, sessionId);

        String aiResponse = aiService.getAIResponse(userMessage);

        Message botMsg = messageService.createBotMessage(aiResponse, sessionId);

        return ResponseEntity.ok(botMsg);
    }
}

