package com.example.final_project.repository.impl.jpa;

import com.example.final_project.models.Notification;
import com.example.final_project.repository.NotificationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface JpaNotificationRepository extends NotificationRepository, JpaRepository<Notification, Long> {
}
