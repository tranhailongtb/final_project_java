package com.example.final_project.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"com.example.final_project.exception"})
public class RequestExceptionHandler {
    @ExceptionHandler(exception = {UserNotFound.class})
    public ResponseEntity<String> notFoundHandler(UserNotFound e) {
        return ResponseEntity.notFound().build();
    }
}
