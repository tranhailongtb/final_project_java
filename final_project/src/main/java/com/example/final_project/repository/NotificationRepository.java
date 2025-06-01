package com.example.final_project.repository;

import com.example.final_project.models.Notification;
import org.springframework.context.annotation.Profile;

import java.util.List;

public interface NotificationRepository {
    List<Notification> findByUserId(Long userId);
    List<Notification> findUnreadByUserId(Long userId);
    Notification save(Notification notification);
}
