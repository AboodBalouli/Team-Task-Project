package com.abood.taskmanager.service;

import com.abood.taskmanager.dto.UserDTO;
import com.abood.taskmanager.dto.UserResponseDTO;
import com.abood.taskmanager.entity.User;
import com.abood.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName()).
                email(userDTO.getEmail()).
                password(userDTO.getPassword()).
                build();
        User savedUser = userRepository.save(user);
        return UserResponseDTO.builder().
                id(savedUser.getId()).
                name(savedUser.getName()).
                email(savedUser.getEmail()).
                build();
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().
                stream()
                .map(user -> UserResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .toList();
    }
}
