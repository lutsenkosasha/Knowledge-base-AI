package org.example.repository;
import org.example.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT s FROM Session s WHERE s.user.userId = :userId AND s.directory.directoryId = :directoryId")
    List<Session> findAllByUserAndDirectory(@Param("userId") Long userId, @Param("directoryId") Long directoryId);

    default Optional<Session> findFirstByUserAndDirectory(Long userId, Long directoryId) {
        List<Session> sessions = findAllByUserAndDirectory(userId, directoryId);
        return sessions.isEmpty() ? Optional.empty() : Optional.of(sessions.get(0));
    }
}

