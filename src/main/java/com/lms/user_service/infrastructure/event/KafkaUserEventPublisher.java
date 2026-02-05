package com.lms.user_service.infrastructure.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.user_service.domain.event.DomainEvent;
import com.lms.user_service.domain.event.UserCreatedEvent;
import com.lms.user_service.domain.ports.UserEventPublisher;
import com.lms.user_service.infrastructure.config.KafkaTopics;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaUserEventPublisher implements UserEventPublisher {
    private final Producer<String, String> producer;
    private final ObjectMapper objectMapper;
    private final String topic;

    public KafkaUserEventPublisher(
            ObjectMapper objectMapper,
            @Value("${spring.kafka.bootstrap-servers:localhost:9092}") String bootstrapServers) {
        this.objectMapper = objectMapper;
        this.topic = KafkaTopics.USER_EVENTS_TOPIC;

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("acks", "all");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        this.producer = new KafkaProducer<>(props);
    }

    @Override
    public void publish(DomainEvent event) {
        String key = null;
        if (event instanceof UserCreatedEvent createdEvent) {
            key = createdEvent.getUserId();
        }

        String payload = serialize(event);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, payload);
        producer.send(record);
    }

    private String serialize(DomainEvent event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to serialize event", e);
        }
    }
}
