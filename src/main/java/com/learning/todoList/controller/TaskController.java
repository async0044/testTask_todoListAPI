package com.learning.todoList.controller;

import com.learning.todoList.dto.TaskRequestDto;
import com.learning.todoList.dto.TaskResponseDto;
import com.learning.todoList.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/todo")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok().body(taskService.addTask(taskRequestDto));
    }

    @GetMapping("/getTaskById")
    public ResponseEntity<TaskResponseDto> getTaskById(@RequestParam @NotNull(message = "ID cannot be null") @Positive(message = "ID must be a positive number") Long id) {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @PatchMapping("/modifyTaskById")
    public ResponseEntity<TaskResponseDto> modifyTaskById(@RequestBody @Valid TaskRequestDto taskRequestDto, @RequestParam @NotNull(message = "ID cannot be null") @Positive(message = "ID must be a positive number") Long id) {
        return ResponseEntity.ok().body(taskService.modifyTaskById(taskRequestDto, id));
    }

    @DeleteMapping("/deleteTaskById")
    public ResponseEntity<TaskResponseDto> deleteTaskById(@RequestParam @NotNull(message = "ID cannot be null") @Positive(message = "ID must be a positive number") Long id) {
        return ResponseEntity.ok().body(taskService.deleteTaskById(id));
    }

    /*
    addTask
    getTask
    modifyTask
    deleteTask
     */

    //TODO прикреплять файлы

    //TODO продолжаем по эндпоинтам
}
