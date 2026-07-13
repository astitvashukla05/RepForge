package com.RepForge.user_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RepForge.user_service.model.ApiResponse;

import com.RepForge.user_service.model.DTOs.RegisterDTO;
import com.RepForge.user_service.model.DTOs.ResponseDTO;
import com.RepForge.user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;

    }

    // GET USER BY ID
    @GetMapping("/{user_id}")
    public ResponseEntity<ApiResponse<ResponseDTO>> getUserById(@PathVariable UUID user_id) {
        ResponseDTO responseDTO = userService.getUserById(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "User Found", responseDTO));

    }

    // REGISTER USER
    @PostMapping
    public ResponseEntity<ApiResponse<ResponseDTO>> registerUser(@Valid @RequestBody RegisterDTO user) {
        ResponseDTO responseDTO = userService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User Registered", responseDTO));
    }

}
