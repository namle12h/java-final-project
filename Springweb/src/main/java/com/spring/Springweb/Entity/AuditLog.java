package com.spring.Springweb.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AuditLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entity;       // Appointment, Invoice, Product...
    private Long entityId;       // ID của record
    private String action;       // CREATE | UPDATE | DELETE | CANCEL
    private String field;        // nếu muốn log chi tiết field thay đổi
    private String oldValue;
    private String newValue;

    private Long performedBy;    // users.id
    private LocalDateTime performedAt;
}
