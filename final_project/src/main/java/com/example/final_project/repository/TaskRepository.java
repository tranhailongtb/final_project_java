package com.example.final_project.repository;

import com.example.final_project.models.Task;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Task> findAll() {
        return tasks.values().stream()
                .filter(task -> !task.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Task> findByUserId(Long userId) {
        return tasks.values().stream()
                .filter(task -> task.getUserId().equals(userId) && !task.isDeleted())
                .collect(Collectors.toList());
    }

    public List<Task> findPendingByUserId(Long userId) {
        return tasks.values().stream()
                .filter(task -> task.getUserId().equals(userId)
                        && !task.isCompleted()
                        && !task.isDeleted())
                .collect(Collectors.toList());
    }

    public Optional<Task> findById(Long id) {
        Task task = tasks.get(id);
        return (task != null && !task.isDeleted()) ? Optional.of(task) : Optional.empty();
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idCounter.getAndIncrement());
            task.setCreationDate(LocalDate.now());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public void markAsDeleted(Long id) {
        Optional.ofNullable(tasks.get(id)).ifPresent(task -> task.setDeleted(true));
    }
}