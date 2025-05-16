package com.example.final_project.repository;

import com.example.final_project.models.Notification;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {
    private final ConcurrentHashMap<Long, Notification> notifications = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Notification> findByUserId(Long userId) {
        return notifications.values().stream()
                .filter(notification -> notification.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Notification> findUnreadByUserId(Long userId) {
        return notifications.values().stream()
                .filter(notification -> notification.getUserId().equals(userId)
                        && !notification.isRead())
                .collect(Collectors.toList());
    }

    public Notification save(Notification notification) {
        if (notification.getId() == null) {
            notification.setId(idCounter.getAndIncrement());
        }
        notifications.put(notification.getId(), notification);
        return notification;
    }
}