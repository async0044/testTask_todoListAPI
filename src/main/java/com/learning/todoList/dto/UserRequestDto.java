package com.learning.todoList.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto (
        @NotBlank(message = "Username cannot be empty")
        @Size(min = 3, max = 30, message = "User must be between 3 and 30 characters")
        String username,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 5, message = "Password must be at least 5 characters long")
        String password,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email chould be valid")
        String email
){
}
