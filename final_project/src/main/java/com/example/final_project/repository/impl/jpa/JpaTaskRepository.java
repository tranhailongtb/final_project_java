package com.example.final_project.repository.impl.jpa;

import com.example.final_project.models.Task;
import com.example.final_project.repository.TaskRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface JpaTaskRepository extends TaskRepository, JpaRepository<Task, Long>{
    @Override
    @Query("UPDATE Task t SET t.deleted = true WHERE t.id = ?1")
    void markAsDeleted(Long id);
}
