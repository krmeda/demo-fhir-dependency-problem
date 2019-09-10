package me.keerthimeda.prototypes.hapidomaindemo.domain;

import lombok.Data;

@Data
public class CustomAuditEvent {
    private String auditMessage;
}
