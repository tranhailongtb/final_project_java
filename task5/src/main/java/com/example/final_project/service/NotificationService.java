package com.example.final_project.service;

import com.example.final_project.dto.response.NotificationResponse;
import com.example.final_project.models.Notification;
import com.example.final_project.models.Task;
import com.example.final_project.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    private static Notification produceFromNewTask(Task task) {
        Notification newNotification = new Notification();
        newNotification.setCreatedAt(LocalDateTime.now()); //should consider use built-in feature
        newNotification.setMessage(task.getTitle().concat(" was created successfully."));
        newNotification.setUserId(task.getUserId());
        return newNotification;
    }

    public List<NotificationResponse> getUserNotifications(Long userId) {
        return NotificationResponse.map(notificationRepository.findByUserId(userId));
    }

    public List<NotificationResponse> getUnreadUserNotifications(Long userId) {
        return NotificationResponse.map(notificationRepository.findByUserIdAndReadFalse(userId));
    }

    public NotificationResponse createNotification(Task task) {
        Notification newNotification = produceFromNewTask(task);
        return NotificationResponse.map(notificationRepository.save(newNotification));
    }

    public void markAsRead(Long id) {
        notificationRepository.markAsRead(id);
    }
}