package com.example.final_project.service;

import com.example.final_project.dto.response.UserResponse;
import com.example.final_project.exception.UserNotFound;
import com.example.final_project.models.User;
import com.example.final_project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(User user) {
        return UserResponse.map(userRepository.save(user));
    }

    public UserResponse login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password)).orElseThrow(UserNotFound::new);
        return UserResponse.map(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFound::new);
        return UserResponse.map(user);
    }
}