package org.example.service;

import org.example.entity.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.example.repository.AuditLogRepository;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void log(String entityName, String operation, String details) {
        AuditLog log = new AuditLog();
        log.setEntityName(entityName);
        log.setOperation(operation);
        log.setTimestamp(LocalDateTime.now());
        log.setDetails(details);
        auditLogRepository.save(log);
    }

    public Page<AuditLog> getAuditLogs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        return auditLogRepository.findAll(pageable);
    }
}