package com.example.final_project.dto.response;

import com.example.final_project.models.Notification;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder // dung Builder khi thuong xuyen khoi tao voi nhieu field
// va khong nhat quan ve fields nao bat buoc
public class NotificationResponse {
    private Long id;
    private Long userId;
    private String message;
    private LocalDateTime createdAt;

//    mapper
    public static NotificationResponse map(Notification task) {
        return NotificationResponse.builder()
                .id(task.getId())
                .userId(task.getUserId())
                .message(task.getMessage())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public static List<NotificationResponse> map(Collection<Notification> tasks) {
        return tasks.stream().map(NotificationResponse::map).toList();
    }
}
