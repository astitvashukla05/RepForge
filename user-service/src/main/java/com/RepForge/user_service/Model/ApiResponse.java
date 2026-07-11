package com.RepForge.user_service.model;

import org.springframework.http.HttpStatus;

import com.RepForge.user_service.model.DTOs.RegisterDTO;
import com.RepForge.user_service.model.DTOs.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
    private ResponseDTO user;
    private RegisterDTO registerUser;
    private HttpStatus status;
}
