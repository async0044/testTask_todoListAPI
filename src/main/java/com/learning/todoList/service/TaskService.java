package com.learning.todoList.service;

import com.learning.todoList.dto.TaskRequestDto;
import com.learning.todoList.dto.TaskResponseDto;
import com.learning.todoList.entity.Task;
import com.learning.todoList.mapper.TaskMapper;
import com.learning.todoList.repository.TaskRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {
        return TaskMapper.taskToDto(taskRepository.save(TaskMapper.dtoToTask(taskRequestDto))); //todo orElseThrow придумать проверки
    }

    public TaskResponseDto getTaskById(Long id) {
        return TaskMapper.taskToDto(taskRepository.findById(id).orElseThrow(() -> new ServiceException("Task with this ID not found")));
    }

    public TaskResponseDto modifyTaskById(TaskRequestDto taskRequestDto, Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ServiceException("Task with this ID not found"));
        task.setTitle(taskRequestDto.title());
        task.setContent(taskRequestDto.content());
        task.setDate(LocalDateTime.now().toString());       //todo проверки, какие поля заполнены
        task.setAuthor(taskRequestDto.author());
        task.setFile1link(taskRequestDto.file1link());
        task.setFile2link(taskRequestDto.file2link());
        return TaskMapper.taskToDto(taskRepository.save(task));
    }

    public TaskResponseDto deleteTaskById(Long id) {
        return TaskMapper.taskToDto(taskRepository.deleteByIdAndReturn(id).orElseThrow(() -> new ServiceException("Task with this ID not found")));
    }
}
