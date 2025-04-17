package org.example.service;

import org.example.entity.Session;
import org.example.entity.User;
import org.example.entity.Message;
import org.example.entity.Directory;
import org.example.repository.DirectoryRepository;
import org.example.repository.MessageRepository;
import org.example.repository.SessionRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Transactional
    public Session createSession(Long userId, Long messageId, Long directoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new RuntimeException("Message not found: " + messageId));
        Directory directory = directoryRepository.findById(directoryId).orElseThrow(() -> new RuntimeException("Directory not found: " + directoryId));
        Session session = new Session();
        session.setUser(user);
        session.setMessage(message);
        session.setDirectory(directory);

        Session saved = sessionRepository.save(session);
        auditLogService.log("Session", "CREATE", "Session created with id: " + saved.getSessionId());
        return saved;
    }

    @Transactional
    public List<Session> createMultipleSessions(List<Session> sessions) {
        for (Session session : sessions) {
            if (session.getUser() == null || session.getUser().getUserId() == null ||
                    session.getMessage() == null || session.getMessage().getMessageId() == null ||
                    session.getDirectory() == null || session.getDirectory().getDirectoryId() == null) {
                throw new RuntimeException("User, Message, and Directory must be specified for each session.");
            }
            User user = userRepository.findById(session.getUser().getUserId()).orElseThrow(() -> new RuntimeException("User not found: " + session.getUser().getUserId()));
            Message message = messageRepository.findById(session.getMessage().getMessageId()).orElseThrow(() -> new RuntimeException("Message not found: " + session.getMessage().getMessageId()));
            Directory directory = directoryRepository.findById(session.getDirectory().getDirectoryId()).orElseThrow(() -> new RuntimeException("Directory not found: " + session.getDirectory().getDirectoryId()));
            session.setUser(user);
            session.setMessage(message);
            session.setDirectory(directory);
        }
        List<Session> saved = sessionRepository.saveAll(sessions);
        auditLogService.log("Session", "BATCH_CREATE", saved.size() + " sessions created.");
        return saved;
    }

    @Transactional
    public Session updateSession(Long id, Session updatedSession) {
        return sessionRepository.findById(id).map(session -> {
            if (updatedSession.getUser() != null && updatedSession.getUser().getUserId() != null) {
                User user = userRepository.findById(updatedSession.getUser().getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found: " + updatedSession.getUser().getUserId()));
                session.setUser(user);
            }
            if (updatedSession.getMessage() != null && updatedSession.getMessage().getMessageId() != null) {
                Message message = messageRepository.findById(updatedSession.getMessage().getMessageId())
                        .orElseThrow(() -> new RuntimeException("Message not found: " + updatedSession.getMessage().getMessageId()));
                session.setMessage(message);
            }
            if (updatedSession.getDirectory() != null && updatedSession.getDirectory().getDirectoryId() != null) {
                Directory directory = directoryRepository.findById(updatedSession.getDirectory().getDirectoryId())
                        .orElseThrow(() -> new RuntimeException("Directory not found: " + updatedSession.getDirectory().getDirectoryId()));
                session.setDirectory(directory);
            }
            Session saved = sessionRepository.save(session);
            auditLogService.log("Session", "UPDATE", "Session updated with id: " + saved.getSessionId());
            return saved;
        }).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    @Transactional
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
        auditLogService.log("Session", "DELETE", "Session deleted with id: " + id);
    }

    public Optional<Session> getSession(Long id) {
        return sessionRepository.findById(id);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}

