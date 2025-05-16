package com.example.final_project.controller;

import com.example.final_project.models.Notification;
import com.example.final_project.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void getUserNotifications_ShouldReturnNotifications() {
        // Arrange
        Long userId = 1L;
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setUserId(userId);

        Notification notification2 = new Notification();
        notification2.setId(2L);
        notification2.setUserId(userId);

        List<Notification> expectedNotifications = Arrays.asList(notification1, notification2);

        when(notificationService.getUserNotifications(userId)).thenReturn(expectedNotifications);

        // Act
        ResponseEntity<List<Notification>> response = notificationController.getUserNotifications(userId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedNotifications, response.getBody());
        verify(notificationService).getUserNotifications(userId);
    }

    @Test
    void getUnreadUserNotifications_ShouldReturnUnreadNotifications() {
        // Arrange
        Long userId = 1L;
        Notification unreadNotification = new Notification();
        unreadNotification.setId(1L);
        unreadNotification.setUserId(userId);
        unreadNotification.setRead(false);

        List<Notification> expectedNotifications = Arrays.asList(unreadNotification);

        when(notificationService.getUnreadUserNotifications(userId)).thenReturn(expectedNotifications);

        // Act
        ResponseEntity<List<Notification>> response = notificationController.getUnreadUserNotifications(userId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedNotifications, response.getBody());
        verify(notificationService).getUnreadUserNotifications(userId);
    }
}