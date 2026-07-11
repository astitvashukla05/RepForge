package com.RepForge.user_service.controller;

import java.util.Optional;
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
        Optional<ResponseDTO> responseDTO = userService.getUserById(user_id);
        if (responseDTO.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User Not Found", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "User Found", responseDTO.get()));

    }

    // REGISTER USER
    @PostMapping("/")
    public ResponseEntity<ApiResponse<ResponseDTO>> registerUser(@Valid @RequestBody RegisterDTO user) {
        Optional<ResponseDTO> responseDTO = userService.registerUser(user);

        if (responseDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "User Not Registered", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(false, "User Registered", responseDTO.get()));
    }

}
