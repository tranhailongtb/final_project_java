package com.example.final_project.controller;

import com.example.final_project.models.User;
import com.example.final_project.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void register_ShouldReturnCreatedUser() {
        // Arrange
        User newUser = new User();
        newUser.setUsername("newuser");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("newuser");

        when(userService.register(newUser)).thenReturn(savedUser);

        // Act
        ResponseEntity<User> response = userController.register(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
        verify(userService).register(newUser);
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setUsername("existinguser");

        when(userService.getUserById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        ResponseEntity<User> response = userController.getUserById(userId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedUser, response.getBody());
        verify(userService).getUserById(userId);
    }

    @Test
    void getUserById_WhenUserNotExists_ShouldReturnNotFound() {
        // Arrange
        Long userId = 99L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<User> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService).getUserById(userId);
    }
}