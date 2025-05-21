package com.example.final_project.repository;

import com.example.final_project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdAndDeletedFalse(Long userId);

    List<Task> findByUserIdAndCompletedFalseAndDeletedFalse(Long userId);

    List<Task> findByDeletedFalse();

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.deleted = true WHERE t.id = ?1")
    void markAsDeleted(Long id);
}