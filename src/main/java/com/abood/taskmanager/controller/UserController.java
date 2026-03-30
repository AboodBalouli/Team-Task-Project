package com.abood.taskmanager.controller;

import com.abood.taskmanager.dto.UserDTO;
import com.abood.taskmanager.dto.UserResponseDTO;
import com.abood.taskmanager.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
