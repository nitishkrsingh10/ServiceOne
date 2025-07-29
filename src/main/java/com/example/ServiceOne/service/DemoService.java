package com.example.ServiceOne.service;

import com.example.ServiceOne.model.TodoItems;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DemoService {
    private final RestTemplate restTemplate;
    private final String demoServiceUrl;

    public DemoService(@Value("${demo.service.url:http://localhost:8081}") String demoServiceUrl) {
        this.restTemplate = new RestTemplate();
        this.demoServiceUrl = demoServiceUrl;
    }

    public List<TodoItems> getUserItems(Integer userId) {
        TodoItems[] items = restTemplate.getForObject(
            demoServiceUrl + "/api/todo/user/" + userId,
            TodoItems[].class
        );
        return items != null ? Arrays.asList(items) : List.of();
    }

    public TodoItems addItem(Integer userId, TodoItems item) {
        return restTemplate.postForObject(
            demoServiceUrl + "/api/todo/add/" + userId,
            item,
            TodoItems.class
        );
    }

    public TodoItems updateItem(Integer id, TodoItems item) {
        return restTemplate.postForObject(
            demoServiceUrl + "/api/todo/edit/" + id,
            item,
            TodoItems.class
        );
    }

    public void deleteItem(Integer id) {
        restTemplate.delete(demoServiceUrl + "/api/todo/delete/" + id);
    }
} 