package com.example.final_project.dto.response;

import com.example.final_project.models.Task;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder // dung Builder khi thuong xuyen khoi tao voi nhieu field
// va khong nhat quan ve fields nao bat buoc.
public class TaskResponse {
    private Long id;
    private Long userId;
    private String title;
    private LocalDate creationDate;
    private LocalDate targetDate;
    private boolean completed;
    private boolean deleted;

    public static TaskResponse map(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .userId(task.getUserId())
                .title(task.getTitle())
                .creationDate(task.getCreationDate())
                .targetDate(task.getTargetDate())
                .completed(task.isCompleted())
                .deleted(task.isDeleted()).build();
    }

    public static List<TaskResponse> map(Collection<Task> tasks) {
        return tasks.stream().map(TaskResponse::map).toList();
    }
}
