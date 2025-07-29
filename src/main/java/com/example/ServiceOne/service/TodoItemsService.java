package com.example.ServiceOne.service;

import com.example.ServiceOne.model.TodoItems;
import com.example.ServiceOne.repository.TodoItemsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TodoItemsService {
    private final TodoItemsRepository todoItemsRepository;
    private final RestTemplate restTemplate;
    private final String demoServiceUrl;

    public TodoItemsService(
            TodoItemsRepository todoItemsRepository,
            @Value("${demo.service.url:http://localhost:8081}") String demoServiceUrl) {
        this.todoItemsRepository = todoItemsRepository;
        this.restTemplate = new RestTemplate();
        this.demoServiceUrl = demoServiceUrl;
    }

    private void verifyUser(Integer userId) {
        try {
            restTemplate.getForObject(
                demoServiceUrl + "/api/users/" + userId,
                Object.class
            );
        } catch (Exception e) {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }

    public TodoItems addTodoItem(Integer userId, TodoItems item) {
        verifyUser(userId);
        item.setUserId(userId);
        return todoItemsRepository.save(item);
    }

    public TodoItems updateTodoItem(Integer id, TodoItems updatedItem) {
        TodoItems existing = todoItemsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo item not found with ID: " + id));

        existing.setDescription(updatedItem.getDescription());
        existing.setStatus(updatedItem.getStatus());

        return todoItemsRepository.save(existing);
    }

    public void deleteTodoItem(Integer id) {
        if (!todoItemsRepository.existsById(id)) {
            throw new EntityNotFoundException("Todo item not found with ID: " + id);
        }
        todoItemsRepository.deleteById(id);
    }

    public List<TodoItems> getItemsForUser(Integer userId) {
        verifyUser(userId);
        return todoItemsRepository.findByUserId(userId);
    }
}
