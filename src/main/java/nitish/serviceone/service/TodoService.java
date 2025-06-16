package nitish.serviceone.service;

import nitish.serviceone.model.Todo;
import nitish.serviceone.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${demo.service.url}")
    private String demoServiceUrl;

    public List<Todo>getAllListTodos(){
        return todoRepository.findAll();
    }

    public List<Todo> getTodosByUserId(Long userId) {
        // Verify user exists in demo service
        if (!verifyUser(userId)) {
            throw new RuntimeException("User not found");
        }
        return todoRepository.findByUserId(userId);
    }

    public Todo createTodo(Long userId, String description) {
        // Verify user exists in demo service
        boolean user = verifyUser(userId);
        System.out.println(user);
        if (!verifyUser(userId)) {
            throw new RuntimeException("User not found");
        }

        Todo todo = new Todo();
        todo.setUserId(userId);
        todo.setDescription(description);
        todo.setStatus("pending");
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long userId, Long todoId, String description, String status) {
        // Verify user exists in demo service
        if (!verifyUser(userId)) {
            throw new RuntimeException("User not found");
        }

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (!todo.getUserId().equals(userId)) {
            throw new RuntimeException("Todo does not belong to user");
        }

        todo.setDescription(description);
        todo.setStatus(status);
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long userId, Long todoId) {
        // Verify user exists in demo service
        if (!verifyUser(userId)) {
            throw new RuntimeException("User not found");
        }

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (!todo.getUserId().equals(userId)) {
            throw new RuntimeException("Todo does not belong to user");
        }

        todoRepository.delete(todo);
    }

    private boolean verifyUser(Long userId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    demoServiceUrl+"/api/users/"+userId,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );


            return response.getStatusCode().is2xxSuccessful() && response.getBody() != null;

        } catch (Exception e) {

            return false;
        }
    }

} 