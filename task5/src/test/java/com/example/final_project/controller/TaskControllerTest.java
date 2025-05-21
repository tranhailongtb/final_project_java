package com.example.final_project.controller;

import com.example.final_project.dto.request.CreateTaskRequest;
import com.example.final_project.dto.response.TaskResponse;
import com.example.final_project.models.Task;
import com.example.final_project.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        TaskResponse task1 = TaskResponse.builder().id(1L).build();
        TaskResponse task2 = TaskResponse.builder().id(2L).build();

        List<TaskResponse> expectedTasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(expectedTasks);
        // Act
        ResponseEntity<List<TaskResponse>> response = taskController.getAllTasks();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedTasks, response.getBody());
        verify(taskService).getAllTasks();
    }

    @Test
    void getUserTasks_ShouldReturnUserTasks() {
        // Arrange
        Long userId = 1L;
        TaskResponse task1 = TaskResponse.builder().userId(userId).build();
        List<TaskResponse> expectedTasks = Collections.singletonList(task1);
        when(taskService.getUserTasks(userId)).thenReturn(expectedTasks);

        // Act
        ResponseEntity<List<TaskResponse>> response = taskController.getUserTasks(userId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedTasks, response.getBody());
        verify(taskService).getUserTasks(userId);
    }

    @Test
    void createTask_ShouldReturnCreatedTask() {
        // Arrange
        CreateTaskRequest taskRequest = new CreateTaskRequest("NewTaskResponse", 1L, LocalDate.now());
        Task expectedTask = new Task();
        expectedTask.setTitle(taskRequest.getTitle());
        expectedTask.setUserId(taskRequest.getUserId());
        expectedTask.setTargetDate(taskRequest.getTargetDate());
        expectedTask.setCreationDate(LocalDate.now());
        when(taskService.createTask(taskRequest)).thenReturn(TaskResponse.map(expectedTask));

        // Act
        ResponseEntity<TaskResponse> response = taskController.createTask(taskRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(taskRequest.getUserId(), response.getBody().getUserId());
        assertEquals(taskRequest.getTitle(), response.getBody().getTitle());
        assertEquals(taskRequest.getTargetDate(), response.getBody().getTargetDate());

        verify(taskService).createTask(taskRequest);
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