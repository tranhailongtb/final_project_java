package com.example.final_project.repository.impl.jpa;

import com.example.final_project.models.User;
import com.example.final_project.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {
}
