package com.example.ServiceOne.controller;

import com.example.ServiceOne.service.Kafkaproducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private Kafkaproducer kafkaproducer;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaproducer.sendMessage(message);
        return "Message sent to Kafka: " + message;
    }
}
