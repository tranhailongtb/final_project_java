package com.example.final_project.repository;

import com.example.final_project.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByUserIdAndReadFalse(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.read = true WHERE n.id = ?1")
    void markAsRead(Long id);
}