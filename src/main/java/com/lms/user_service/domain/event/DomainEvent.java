package com.lms.user_service.domain.event;

import java.time.Instant;

public abstract class DomainEvent {
    private final String eventType;
    private final Instant occurredAt;

    protected DomainEvent(String eventType) {
        this.eventType = eventType;
        this.occurredAt = Instant.now();
    }

    public String getEventType() {
        return eventType;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
