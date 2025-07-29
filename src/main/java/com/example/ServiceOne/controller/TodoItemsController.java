package com.example.ServiceOne.controller;

import com.example.ServiceOne.model.TodoItems;
import com.example.ServiceOne.service.DemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoItemsController {

    private final DemoService demoService;

    public TodoItemsController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TodoItems>> getUserItems(@PathVariable Integer userId) {
        return ResponseEntity.ok(demoService.getUserItems(userId));
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<TodoItems> addItem(@PathVariable Integer userId, @RequestBody TodoItems item) {
        return ResponseEntity.ok(demoService.addItem(userId, item));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TodoItems> updateItem(@PathVariable Integer id, @RequestBody TodoItems item) {
        return ResponseEntity.ok(demoService.updateItem(id, item));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer id) {
        demoService.deleteItem(id);
        return ResponseEntity.ok("Item deleted successfully.");
    }
}
