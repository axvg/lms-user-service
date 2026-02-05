package com.lms.user_service.domain.ports;

import com.lms.user_service.domain.event.DomainEvent;

public interface UserEventPublisher {
    void publish(DomainEvent event);
}
