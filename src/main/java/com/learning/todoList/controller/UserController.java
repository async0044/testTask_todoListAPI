package com.learning.todoList.controller;


import com.learning.todoList.dto.UserRequestDto;
import com.learning.todoList.dto.UserResponseDto;
import com.learning.todoList.service.UserService;
import com.learning.todoList.util.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> addUser (@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(userService.addUser(userRequestDto));
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserResponseDto> getUserById (@RequestParam("id") @NotNull(message = "ID cannot be null") @Positive(message = "ID must be a positive number") Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<UserResponseDto> getUserByUsername (@RequestParam("username") @NotBlank(message = "Username cannot be empty") String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserResponseDto> getUserByEmail (@RequestParam("email") @Email @NotBlank(message = "Email cannot be empty") String email) {
        return ResponseEntity.ok().body(userService.getUserByEmail(email));
    }

    @GetMapping("/getUserList")
    public ResponseEntity<List<UserResponseDto>> getUserList () {
        return ResponseEntity.ok().body(userService.getUserList());
    }

    @PatchMapping("/updateUserById")
    public ResponseEntity<UserResponseDto> updateUserById (@RequestParam @NotNull(message = "ID cannot be null") @Positive(message = "ID must be a positive number") Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(userService.updateUserById(id, userRequestDto));
    }

    //TODO переделать updateUser:
    //  GetMapping отсылает ДТО юзера, юзер переписывает там и возвращает исправленный ДТО, и сервис меняет исправленные поля в БД, как-то так

    @DeleteMapping("/deleteUserById")
    public ResponseEntity<UserResponseDto> deleteUserById (@RequestParam @NotNull(message = "ID cannot be null") @Positive(message = "ID must be a positive number") Long id) {
        return ResponseEntity.ok().body(userService.deleteUserById(id));
    }

    @PatchMapping("/changeStatusById")
    public ResponseEntity<UserResponseDto> changeStatusById(@RequestParam @NotNull(message = "ID cannot be null") @Positive(message = "ID must be a positive number") Long id, @RequestParam Status status) {
        return ResponseEntity.ok().body(userService.changeStatusById(id, status));
    }

    /*
    addUser
    getUserById
    getUserByUsername
    getUserByEmail
    getUserList
    modifyUserById
    deleteUser
    stopUserActually
     */

    //TODO авторизация пользователя
}
