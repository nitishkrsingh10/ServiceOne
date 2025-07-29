package com.example.ServiceOne.repository;

import com.example.ServiceOne.model.TodoItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemsRepository extends JpaRepository<TodoItems, Integer> {
    List<TodoItems> findByUserId(Integer userId);
}
