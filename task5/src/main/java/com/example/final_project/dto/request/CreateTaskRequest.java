package com.example.final_project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor // dung all args constructor khi thuong xuyen khoi tao voi tat ca cac field
public class CreateTaskRequest {
    String title;
    Long userId;
    LocalDate targetDate;
}
