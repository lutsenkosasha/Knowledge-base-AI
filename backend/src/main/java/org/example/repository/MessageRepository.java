package org.example.repository;
import jakarta.transaction.Transactional;
import org.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Modifying
    @Transactional
    void deleteBySession_SessionId(Long sessionId);

    @Query("SELECT m FROM Message m WHERE m.session.sessionId = :sessionId ORDER BY m.date ASC, m.time ASC")
    List<Message> findBySessionId(@Param("sessionId") Long sessionId);

    @Query("SELECT m FROM Message m WHERE m.session.sessionId = :sessionId ORDER BY m.date, m.time")
    List<Message> findBySessionIdOrdered(@Param("sessionId") Long sessionId);
}
