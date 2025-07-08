package com.learning.todoList.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequestDto(
        @NotBlank(message = "Title cannot be empty")
        @Size(min = 3, message = "Title must be at least 3 characters long")
        String title,
        String content,
        String author,
        String file1link,
        String file2link
) {
}
