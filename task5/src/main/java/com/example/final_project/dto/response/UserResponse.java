package com.example.final_project.dto.response;

import com.example.final_project.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;

    public static UserResponse map(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }

    public static List<UserResponse> map(Collection<User> users) {
        return users.stream().map(UserResponse::map).toList();
    }
}
