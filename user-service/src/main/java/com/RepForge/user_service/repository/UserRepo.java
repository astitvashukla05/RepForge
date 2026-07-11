package com.RepForge.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RepForge.user_service.model.UserModel;
import com.RepForge.user_service.model.DTOs.ResponseDTO;

public interface UserRepo extends JpaRepository<UserModel, Integer> {
    public Optional<ResponseDTO> findByUUID(String user_id);

    public Optional<ResponseDTO> findByEmail(String email);
}
