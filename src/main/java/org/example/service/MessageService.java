package org.example.service;

import org.example.entity.Message;
import org.example.entity.Session;
import org.example.repository.MessageRepository;
import org.example.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Transactional
    public Message createMessage(Message message, Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found: " + sessionId));
        message.setSession(session);
        Message saved = messageRepository.save(message);
        auditLogService.log("Message", "CREATE", "Message created with id: " + saved.getMessageId());
        return saved;
    }

    @Transactional
    public List<Message> createMultipleMessages(List<Message> messages) {
        for (Message message : messages) {
            if (message.getSession() == null || message.getSession().getSessionId() == null) {
                throw new RuntimeException("Session must be specified for each message.");
            }
            Session session = sessionRepository.findById(message.getSession().getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found: " + message.getSession().getSessionId()));
            message.setSession(session);
        }
        List<Message> saved = messageRepository.saveAll(messages);
        auditLogService.log("Message", "BATCH_CREATE", saved.size() + " messages created.");
        return saved;
    }

    @Transactional
    public Message updateMessage(Long id, Message updatedMessage) {
        return messageRepository.findById(id).map(message -> {
            message.setTextMessage(updatedMessage.getTextMessage());
            message.setDate(updatedMessage.getDate());
            message.setTime(updatedMessage.getTime());
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
