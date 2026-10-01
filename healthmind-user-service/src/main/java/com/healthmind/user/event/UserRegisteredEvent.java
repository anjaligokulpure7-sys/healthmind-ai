package com.healthmind.user.event;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserRegisteredEvent {
    private UUID userId;
    private String email;
    private String fullName;
    private String role;
    private String correlationId;

    @Builder.Default
    private Instant occurredAt = Instant.now();
}
