package org.example.controller;

import org.example.entity.AuditLog;
import org.example.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    @Autowired
    private AuditLogService auditService;

    @GetMapping
    public Page<AuditLog> getAuditLogs(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return auditService.getAuditLogs(page, size);
    }
}
