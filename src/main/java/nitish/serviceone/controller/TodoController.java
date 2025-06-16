package nitish.serviceone.controller;

import nitish.serviceone.model.Todo;
import nitish.serviceone.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Todo>> getTodosByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(todoService.getTodosByUserId(userId));
    }
    @GetMapping("/user")
    public ResponseEntity<List<Todo>> getAllTodosList(){
        return ResponseEntity.ok(todoService.getAllListTodos());
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Todo> createTodo(
            @PathVariable Long userId,
            @RequestParam String description) {
        return ResponseEntity.ok(todoService.createTodo(userId, description));
    }

    @PutMapping("/{todoId}/user/{userId}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable Long todoId,
            @PathVariable Long userId,
            @RequestParam String description,
            @RequestParam String status) {
        return ResponseEntity.ok(todoService.updateTodo(userId, todoId, description, status));
    }

    @DeleteMapping("/{todoId}/user/{userId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long todoId,
            @PathVariable Long userId) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.ok().build();
    }
} 