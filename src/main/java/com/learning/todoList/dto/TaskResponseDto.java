package com.learning.todoList.dto;

import com.learning.todoList.util.Status;

public record TaskResponseDto(
        Long id,
        String title,
        String content,
        String date,
        String author,
        Status status,
        String file1link,
        String file2link
) {
}
