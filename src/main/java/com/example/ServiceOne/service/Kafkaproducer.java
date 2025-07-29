package com.example.ServiceOne.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Kafkaproducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "my-topic";

    public Kafkaproducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Produced message: " + message);
    }
}
