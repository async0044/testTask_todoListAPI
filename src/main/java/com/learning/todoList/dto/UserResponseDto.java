package com.learning.todoList.dto;

public record UserResponseDto (
        Long id,
        String username,
        String email,
        String status
)
{ }
