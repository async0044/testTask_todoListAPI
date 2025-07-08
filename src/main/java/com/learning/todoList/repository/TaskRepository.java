package com.learning.todoList.repository;

import com.learning.todoList.entity.Task;
import com.learning.todoList.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    default Optional<Task> deleteByIdAndReturn(Long id) {
        Optional<Task> optional = findById(id);
        optional.ifPresent(this::delete);
        return optional;
    }
}
