package com.learning.todoList.dto;

import com.learning.todoList.util.Status;

public record UserResponseDto (
        Long id,
        String username,
        String email,
        Status status
)
{ }
