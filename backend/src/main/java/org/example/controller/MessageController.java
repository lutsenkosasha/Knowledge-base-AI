package org.example.controller;

import org.example.entity.Message;
import org.example.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user/{sessionId}")
    public ResponseEntity<Message> createUserMessage(
            @PathVariable Long sessionId,
            @RequestBody String text
    ) {
        return ResponseEntity.ok(messageService.createUserMessage(text, sessionId));
    }

    @PostMapping("/bot/{sessionId}")
    public ResponseEntity<Message> createBotMessage(
            @PathVariable Long sessionId,
            @RequestBody String text
    ) {
        return ResponseEntity.ok(messageService.createBotMessage(text, sessionId));
    }

    @PostMapping("/batch/session/{sessionId}")
    public ResponseEntity<List<Message>> createMessages(
            @PathVariable Long sessionId,
            @RequestBody List<Message> messages
    ) {
        return ResponseEntity.ok(messageService.createMultipleMessages(messages, sessionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        return messageService.getMessage(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessagesBySession(
            @RequestParam Long sessionId) {

        return ResponseEntity.ok(messageService.getMessagesBySessionId(sessionId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(
            @PathVariable Long id,
            @RequestBody Message message
    ) {
        return ResponseEntity.ok(messageService.updateMessage(id, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<Void> deleteMessagesBySessionId(@PathVariable Long sessionId) {
        messageService.deleteMessagesBySessionId(sessionId);
        return ResponseEntity.noContent().build();
    }
}
