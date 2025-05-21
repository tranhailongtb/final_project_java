package com.example.final_project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserRequest {
    private String username;
    private String password;
}
