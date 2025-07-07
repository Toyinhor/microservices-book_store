package com.microservice.notify_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "borrow-events", groupId = "notify-group")
    public void listen(String message) {
        log.info("📩 Received Kafka message: {}", message);
    }
}

