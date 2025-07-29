package com.example.ServiceOne.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Kafkaconsumer {

    @KafkaListener(topics = "my-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
