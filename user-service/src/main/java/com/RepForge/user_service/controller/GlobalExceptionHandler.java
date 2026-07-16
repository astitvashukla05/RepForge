package com.RepForge.user_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.RepForge.user_service.exceptions.UserAlreadyExistsException;
import com.RepForge.user_service.exceptions.UserNotFoundException;
import com.RepForge.user_service.model.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException exception) {
            ApiResponse<String> userNotFound=new ApiResponse<>
            (false,"Something went wrong, please try again later"
            ,"User Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> userAlreadyExists(UserAlreadyExistsException exception) {
        ApiResponse<String> userExists = new ApiResponse<>(false,
            "Something went wrong.Please try again later.",
                "User Already Exists please login");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userExists);
    }

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {

        ApiResponse<String> error = new ApiResponse<>(
            false,
                "Internal Server Error",
                ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

}
