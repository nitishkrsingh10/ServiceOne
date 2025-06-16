package nitish.serviceone.repository;

import nitish.serviceone.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // This is also use because this is not inbuilt persent in JpaRepository so need this function
    List<Todo> findByUserId(Long userId);

    // this is use because this not inbuilt persent in JpaRepository so need this function
    void deleteByUserIdAndId(Long userId, Long id);
} 