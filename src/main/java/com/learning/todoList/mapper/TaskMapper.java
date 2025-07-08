package com.learning.todoList.mapper;

import com.learning.todoList.dto.TaskRequestDto;
import com.learning.todoList.dto.TaskResponseDto;
import com.learning.todoList.entity.Task;
import com.learning.todoList.util.Status;

import java.time.LocalDateTime;

public class TaskMapper {

    public static TaskResponseDto taskToDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getDate(),
                task.getAuthor(),
                task.getStatus(),
                task.getFile1link(),
                task.getFile2link());
    }

    public static Task dtoToTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.title());
        task.setContent(taskRequestDto.content());
        task.setAuthor(taskRequestDto.author());
        task.setDate(LocalDateTime.now().toString());
        task.setStatus(Status.STATUS_ACTIVE);
        task.setFile1link(taskRequestDto.file1link());
        task.setFile2link(taskRequestDto.file2link());
        return task;
    }

}
