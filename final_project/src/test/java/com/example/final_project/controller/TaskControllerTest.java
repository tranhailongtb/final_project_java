package com.example.final_project.controller;

import com.example.final_project.models.Task;
import com.example.final_project.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getAllTasks_ShouldReturnAllTasks() {
        // Arrange
        Task task1 = new Task();
        task1.setId(1L);

        Task task2 = new Task();
        task2.setId(2L);

        List<Task> expectedTasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(expectedTasks);

        // Act
        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedTasks, response.getBody());
        verify(taskService).getAllTasks();
    }

    @Test
    void getUserTasks_ShouldReturnUserTasks() {
        // Arrange
        Long userId = 1L;
        Task task1 = new Task();
        task1.setId(1L);
        task1.setUserId(userId);

        List<Task> expectedTasks = Arrays.asList(task1);

        when(taskService.getUserTasks(userId)).thenReturn(expectedTasks);

        // Act
        ResponseEntity<List<Task>> response = taskController.getUserTasks(userId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedTasks, response.getBody());
        verify(taskService).getUserTasks(userId);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() {
        // Arrange
        Task newTask = new Task();
        newTask.setTitle("New Task");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("New Task");

        when(taskService.createTask(newTask)).thenReturn(savedTask);

        // Act
        ResponseEntity<Task> response = taskController.createTask(newTask);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedTask, response.getBody());
        verify(taskService).createTask(newTask);
    }

    @Test
    void deleteTask_ShouldReturnNoContent() {
        // Arrange
        Long taskId = 1L;
        doNothing().when(taskService).deleteTask(taskId);

        // Act
        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService).deleteTask(taskId);
    }
}