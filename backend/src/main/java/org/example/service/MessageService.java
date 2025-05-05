package org.example.service;

import org.example.entity.Message;
import org.example.entity.Session;
import org.example.repository.MessageRepository;
import org.example.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final SessionRepository sessionRepository;
    private final AuditLogService auditLogService;

    @Autowired
    public MessageService(MessageRepository messageRepository,
                          SessionRepository sessionRepository,
                          AuditLogService auditLogService) {
        this.messageRepository = messageRepository;
        this.sessionRepository = sessionRepository;
        this.auditLogService = auditLogService;
    }

    @Transactional
    public Message createUserMessage(String text, Long sessionId) {
        Message message = new Message();
        message.setText(text);
        message.setDate(LocalDate.now());
        message.setTime(LocalTime.of(0, 0));
        return saveMessageWithLogging(message, sessionId, "USER_MESSAGE");
    }

    @Transactional
    public Message createBotMessage(String text, Long sessionId) {
        Message message = new Message();
        message.setText(text);
        message.setDate(LocalDate.now());
        message.setTime(LocalTime.of(0, 0));
        return saveMessageWithLogging(message, sessionId, "BOT_MESSAGE");
    }

    private Message saveMessageWithLogging(Message message, Long sessionId, String messageType) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found: " + sessionId));

        message.setSession(session);
        session.addMessage(message);

        Message saved = messageRepository.save(message);
        auditLogService.log("Message", "CREATE",
                "Message (type: %s) created with id: %s".formatted(messageType, saved.getMessageId()));

        return saved;
    }

    @Transactional
    public List<Message> createMultipleMessages(List<Message> messages, Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found: " + sessionId));

        messages.forEach(message -> {
            message.setSession(session);
            message.setDate(LocalDate.now());
            message.setTime(LocalTime.now());
            messageRepository.save(message);
            session.addMessage(message);
        });

        auditLogService.log("Message", "BATCH_CREATE", messages.size() + " messages created.");
        return messages;
    }

    @Transactional
    public Message updateMessage(Long id, Message updatedMessage) {
        return messageRepository.findById(id).map(message -> {
            message.setText(updatedMessage.getText());
            message.setDate(LocalDate.now());
            message.setTime(LocalTime.now());

            Message saved = messageRepository.save(message);
            auditLogService.log("Message", "UPDATE", "Message updated with id: " + saved.getMessageId());
            return saved;
        }).orElseThrow(() -> new RuntimeException("Message not found"));
    }

    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
        auditLogService.log("Message", "DELETE", "Message deleted with id: " + id);
    }

    public Optional<Message> getMessage(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}

