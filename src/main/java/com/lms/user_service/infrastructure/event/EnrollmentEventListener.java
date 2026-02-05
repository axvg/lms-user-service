package com.lms.user_service.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentEventListener {
    private static final Logger log = LoggerFactory.getLogger(EnrollmentEventListener.class);

    @KafkaListener(topics = "lms.enrollment.events", groupId = "${spring.kafka.consumer.group-id}")
    public void handleEnrollmentEvents(String payload) {
        log.info("Received enrollment event: {}", payload);
    }
}
