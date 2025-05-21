package com.example.final_project.controller;

import com.example.final_project.dto.response.NotificationResponse;
import com.example.final_project.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        NotificationResponse notification1 = NotificationResponse.builder().userId(userId).build();
        NotificationResponse notification2 = NotificationResponse.builder().userId(userId).build();

        List<NotificationResponse> expectedNotifications = Arrays.asList(notification1, notification2);

        when(notificationService.getUserNotifications(userId)).thenReturn(expectedNotifications);

        // Act
        ResponseEntity<List<NotificationResponse>> response = notificationController.getUserNotifications(userId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedNotifications, response.getBody());
        verify(notificationService).getUserNotifications(userId);
    }

//    @Test
//    void getUnreadUserNotifications_ShouldReturnUnreadNotifications() {
//        // Arrange
//        Long userId = 1L;
//        NotificationResponse unreadNotification = NotificationResponse.builder()
//                .userId(userId);
//        unreadNotification.setUserId(userId);
//        unreadNotification.setRead(false);
//
//        List<Notification> expectedNotifications = List.of(unreadNotification);
//
//        when(notificationService.getUnreadUserNotifications(userId)).thenReturn(expectedNotifications);
//
//        // Act
//        ResponseEntity<List<NotificationResponse>> response = notificationController.getUnreadUserNotifications(userId);
//
//        // Assert
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals(expectedNotifications, response.getBody());
//        verify(notificationService).getUnreadUserNotifications(userId);
//    }
}