package com.learning.todoList.service;

import com.learning.todoList.dto.UserRequestDto;
import com.learning.todoList.dto.UserResponseDto;
import com.learning.todoList.entity.User;
import com.learning.todoList.exception.AddUserException;
import com.learning.todoList.mapper.UserMapper;
import com.learning.todoList.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.username())) throw new AddUserException("Username already exists");

        if (userRepository.existsByEmail(userRequestDto.email())) throw new AddUserException("Email already exists");     //todo переделать на что-то типа CustomServiceException
/*
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setPassword(new BCryptPasswordEncoder(12).encode(userRequestDto.password()));
        user.setEmail(userRequestDto.email());
        user.setRole("ROLE_USER");
        user.setStatus("ACTIVE");
        return UserMapper.userToDto(userRepository.save(user)); //todo: обработать ошибки БД
 */
        return UserMapper.userToDto(userRepository.save(UserMapper.dtoToUser(userRequestDto)));
    }

    public UserResponseDto getUserById(Long id) {
        return UserMapper.userToDto(userRepository.findById(id).orElseThrow(() -> new ServiceException("User with this ID not found")));
    }

    public UserResponseDto getUserByUsername(String username) {
        return UserMapper.userToDto(userRepository.findByUsername(username).orElseThrow(() -> new ServiceException("User with this Username not found")));
    }

    public UserResponseDto getUserByEmail(String email) {
        return UserMapper.userToDto(userRepository.findByEmail(email).orElseThrow(() -> new ServiceException("User with this Email not found")));
    }

    public List<UserResponseDto> getUserList() {
        List<UserResponseDto> userList = new ArrayList<>();
        userRepository.findAll().forEach(user -> userList.add(UserMapper.userToDto(user)));
        return userList;
    }

    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException("User with this ID not found"));
        user.setUsername(userRequestDto.username());
        user.setEmail(userRequestDto.email());
        user.setPassword(new BCryptPasswordEncoder(12).encode(userRequestDto.password()));  //todo переделать под UserMapper
        user.setRole("ROLE_USER");
        user.setStatus("ACTIVE");
        return UserMapper.userToDto(userRepository.save(user));
    }

    public UserResponseDto deleteUserById(Long id) {
        //return UserMapper.userToDto(userRepository.deleteByIdAndReturn(id).orElseThrow(() -> new ServiceException("User with this ID not found")));
        return userRepository.deleteByIdAndReturn(id).map(UserMapper::userToDto).orElseThrow(() -> new ServiceException("User with this ID not found"));
    }
//

}
