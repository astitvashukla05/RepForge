package com.RepForge.activity_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.RepForge.activity_service.exceptions.ActivityNotExists;
import com.RepForge.activity_service.exceptions.UserNotFoundException;
import com.RepForge.activity_service.model.ApiResponse;

@RestControllerAdvice
public class GlobalException {

    // Validate activity id
    @ExceptionHandler(ActivityNotExists.class)
    public ResponseEntity<ApiResponse<String>> activityIdNotExists(ActivityNotExists e) {
        ApiResponse<String> error = new ApiResponse<>(false, "Activity does not exists", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> userNotFound(UserNotFoundException e) {

        ApiResponse<String> error = new ApiResponse<>(false, "User does not exists", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // For Validation Fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    // Generic Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        ApiResponse<String> error = new ApiResponse<>(false, "Internal Server Error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
