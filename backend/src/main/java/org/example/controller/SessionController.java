package org.example.controller;

import org.example.dto.SessionDto;
import org.example.entity.Directory;
import org.example.entity.Session;
import org.example.entity.Message;
import org.example.entity.User;
import org.example.service.DirectoryService;
import org.example.service.SessionService;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    private final UserService userService;

    private final DirectoryService directoryService;

    public SessionController(SessionService sessionService, UserService userService, DirectoryService directoryService) {
        this.sessionService = sessionService;
        this.userService = userService;
        this.directoryService = directoryService;
    }

    @PostMapping
    public ResponseEntity<SessionDto> createSession(
            @RequestParam Long userId,
            @RequestParam Long directoryId
    ) throws Exception {
        User user = userService.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        Directory directory = directoryService.findById(directoryId)
                .orElseThrow(() -> new Exception("Directory not found"));

        Session newSession = sessionService.createSession(userId, directoryId);
        SessionDto dto = new SessionDto();
        dto.setSessionId(newSession.getSessionId());
        dto.setUserName(newSession.getUser().getName());
        dto.setDirectoryName(newSession.getDirectory().getDirectoryName());

        return ResponseEntity.ok(dto);
    }


    @PostMapping("/{sessionId}/messages")
    public ResponseEntity<Message> addMessageToSession(
            @PathVariable Long sessionId,
            @RequestBody Message message
    ) {
        sessionService.addMessageToSession(sessionId, message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSession(@PathVariable Long id) {
        return sessionService.getSession(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(
            @PathVariable Long id,
            @RequestBody Session session
    ) {
        return ResponseEntity.ok(sessionService.updateSession(id, session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
