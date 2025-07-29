package com.example.ServiceOne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = {"com.example.ServiceOne"})
@EntityScan(basePackages = {"com.example.ServiceOne.model"})
public class ServiceOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOneApplication.class, args);
    }
}
