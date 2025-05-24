package com.example.final_project.service;

import com.example.final_project.models.Task;
import com.example.final_project.repository.TaskRepository;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final KafkaTemplate<String, Task> kafkaTemplate;

    public TaskService(TaskRepository taskRepository, KafkaTemplate<String, Task> kafkaTemplate) {
        this.taskRepository = taskRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findByDeletedFalse();
    }

    @Cacheable(cacheNames = "user_task", key = "#userId")
    public List<Task> getUserTasks(Long userId) {
        System.out.printf("Cache missed: %s\n", userId);
        return taskRepository.findByUserIdAndDeletedFalse(userId);
    }

    public List<Task> getPendingUserTasks(Long userId) {
        return taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId);
    }

    public Task createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        kafkaTemplate.send("tasks", savedTask);
        return savedTask;
    }

    public void deleteTask(Long id) {
        taskRepository.markAsDeleted(id);
    }
}