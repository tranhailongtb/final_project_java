package com.example.final_project.service;

import com.example.final_project.models.Notification;
import com.example.final_project.models.Task;
import com.example.final_project.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getUnreadUserNotifications(Long userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @KafkaListener(topics = "tasks", groupId = "task-group")
    public Notification createNotification(Task task) {
        System.out.printf("Receive task %s%n\n", task);
        Notification newNotification = new Notification();
        newNotification.setCreatedAt(LocalDateTime.now()); //should consider use built-in feature
        newNotification.setMessage(task.getTitle().concat(" was created successfully."));
        newNotification.setUserId(task.getUserId());
        return notificationRepository.save(newNotification);
    }
    public void markAsRead(Long id) {
        notificationRepository.markAsRead(id);
    }
}