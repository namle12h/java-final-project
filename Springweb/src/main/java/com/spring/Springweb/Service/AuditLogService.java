package com.spring.Springweb.Service;

import com.spring.Springweb.Entity.AuditLog;
import com.spring.Springweb.Repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public void log(String entity, Long entityId, String action,
                    String field, String oldValue, String newValue, Long performedBy) {
        AuditLog log = AuditLog.builder()
                .entity(entity)
                .entityId(entityId)
                .action(action)
                .field(field)
                .oldValue(oldValue)
                .newValue(newValue)
                .performedBy(performedBy)
                .performedAt(LocalDateTime.now())
                .build();
        auditLogRepository.save(log);
    }

    public void logCreate(String entity, Long entityId, Long performedBy) {
        log(entity, entityId, "CREATE", null, null, null, performedBy);
    }

    public void logDelete(String entity, Long entityId, Long performedBy) {
        log(entity, entityId, "DELETE", null, null, null, performedBy);
    }

    public void logUpdate(String entity, Long entityId, String field,
                          String oldValue, String newValue, Long performedBy) {
        log(entity, entityId, "UPDATE", field, oldValue, newValue, performedBy);
    }
}
