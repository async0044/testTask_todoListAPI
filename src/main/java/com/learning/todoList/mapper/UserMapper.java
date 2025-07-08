package com.learning.todoList.mapper;

import com.learning.todoList.dto.UserRequestDto;
import com.learning.todoList.dto.UserResponseDto;
import com.learning.todoList.entity.User;
import com.learning.todoList.util.Role;
import com.learning.todoList.util.Status;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {


    public static UserResponseDto userToDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getStatus());
    }

    public static User dtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setPassword(new BCryptPasswordEncoder(12).encode(userRequestDto.password()));
        user.setEmail(userRequestDto.email());
        user.setRole(Role.ROLE_USER);
        user.setStatus(Status.STATUS_ACTIVE);
        return user;
    }
}
