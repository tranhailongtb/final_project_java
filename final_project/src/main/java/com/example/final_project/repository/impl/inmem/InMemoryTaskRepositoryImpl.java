package com.example.final_project.repository.impl.inmem;

import com.example.final_project.models.Task;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.example.final_project.repository.TaskRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("in-memory")
public class InMemoryTaskRepositoryImpl implements TaskRepository {
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override public List<Task> findAll() {
        return tasks.values().stream()
                .filter(task -> !task.isDeleted())
                .collect(Collectors.toList());
    }

    @Override public List<Task> findByUserId(Long userId) {
        return tasks.values().stream()
                .filter(task -> task.getUserId().equals(userId) && !task.isDeleted())
                .collect(Collectors.toList());
    }

    @Override public List<Task> findPendingByUserId(Long userId) {
        return tasks.values().stream()
                .filter(task -> task.getUserId().equals(userId)
                        && !task.isCompleted()
                        && !task.isDeleted())
                .collect(Collectors.toList());
    }

    @Override public Optional<Task> findById(Long id) {
        Task task = tasks.get(id);
        return (task != null && !task.isDeleted()) ? Optional.of(task) : Optional.empty();
    }

    @Override public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idCounter.getAndIncrement());
            task.setCreationDate(LocalDate.now());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override public void markAsDeleted(Long id) {
        Optional.ofNullable(tasks.get(id)).ifPresent(task -> task.setDeleted(true));
    }
}