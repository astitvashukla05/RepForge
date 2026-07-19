package com.RepForge.activity_service.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.RepForge.activity_service.model.ApiResponse;
import com.RepForge.activity_service.model.DTOs.ResponseDTO;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserClient {
    @GetMapping("/api/users/{userId}")
    ApiResponse<ResponseDTO> getUserById(@PathVariable UUID userId);
}
