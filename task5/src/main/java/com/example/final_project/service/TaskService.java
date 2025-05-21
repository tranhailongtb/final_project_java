package com.example.final_project.service;

import com.example.final_project.dto.request.CreateTaskRequest;
import com.example.final_project.dto.response.TaskResponse;
import com.example.final_project.models.Task;
import com.example.final_project.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private static Task defaultCreate(CreateTaskRequest taskRequest) {
        Task newTask = new Task();
        newTask.setTitle(taskRequest.getTitle());
        newTask.setUserId(taskRequest.getUserId());
        newTask.setTargetDate(taskRequest.getTargetDate());
        newTask.setCreationDate(LocalDate.now());
        return newTask;
    }

    public List<TaskResponse> getAllTasks() {
        return TaskResponse.map(taskRepository.findByDeletedFalse());
    }

    public List<TaskResponse> getUserTasks(Long userId) {
        return TaskResponse.map(taskRepository.findByUserIdAndDeletedFalse(userId));
    }

    public List<TaskResponse> getPendingUserTasks(Long userId) {
        return TaskResponse.map(taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId));
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        return TaskResponse.map(taskRepository.save(defaultCreate(request)));
    }

    public void deleteTask(Long id) {
        taskRepository.markAsDeleted(id);
    }
}