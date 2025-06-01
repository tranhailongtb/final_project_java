package com.example.final_project.repository;

import com.example.final_project.models.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);
}
