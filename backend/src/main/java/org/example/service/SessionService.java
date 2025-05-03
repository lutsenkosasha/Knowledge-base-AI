package org.example.service;

import org.example.entity.Session;
import org.example.entity.User;
import org.example.entity.Directory;
import org.example.entity.Message;
import org.example.repository.DirectoryRepository;
import org.example.repository.SessionRepository;
import org.example.repository.UserRepository;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final DirectoryRepository directoryRepository;
    private final AuditLogService auditLogService;

    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          UserRepository userRepository,
                          DirectoryRepository directoryRepository,
                          AuditLogService auditLogService) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.directoryRepository = directoryRepository;
        this.auditLogService = auditLogService;
    }

    @Transactional
    public Session createSession(Long userId, Long directoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(() -> new RuntimeException("Directory not found: " + directoryId));

        Session session = new Session();
        session.setUser(user);
        session.setDirectory(directory);

        Session saved = sessionRepository.save(session);
        auditLogService.log("Session", "CREATE", "Session created with id: " + saved.getSessionId());
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
        sessionRepository.findById(id).ifPresent(session -> {
            session.getMessages().forEach(message -> message.setSession(null));
            sessionRepository.delete(session);
            auditLogService.log("Session", "DELETE", "Session deleted with id: " + id);
        });
    }

    public Optional<Session> getSession(Long id) {
        return sessionRepository.findById(id);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Transactional
    public void addMessageToSession(Long sessionId, Message message) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        session.addMessage(message);
    }
}
