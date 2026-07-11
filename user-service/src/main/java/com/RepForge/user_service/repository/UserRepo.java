package com.RepForge.user_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RepForge.user_service.model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByEmail(String email);
}
