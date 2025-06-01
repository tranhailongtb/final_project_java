package com.example.final_project.repository;

import com.example.final_project.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    List<Task> findByUserId(Long userId);

    List<Task> findPendingByUserId(Long userId);

    Optional<Task> findById(Long id);

    Task save(Task task);

    void markAsDeleted(Long id);
}
