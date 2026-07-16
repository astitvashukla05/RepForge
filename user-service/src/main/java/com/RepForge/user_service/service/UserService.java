package com.RepForge.user_service.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.RepForge.user_service.exceptions.UserAlreadyExistsException;
import com.RepForge.user_service.exceptions.UserNotFoundException;
import com.RepForge.user_service.model.UserModel;
import com.RepForge.user_service.model.DTOs.RegisterDTO;
import com.RepForge.user_service.model.DTOs.ResponseDTO;
import com.RepForge.user_service.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    UserService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;

    }

    public ResponseDTO getUserById(UUID user_id) {

        UserModel user = userRepo.findById(user_id).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setEmail(user.getEmail());
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        return responseDTO;
    }

    public ResponseDTO registerUser(RegisterDTO user) {

        Optional<UserModel> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User Already Exists with email : " + user.getEmail());
        }

        UserModel newUser = new UserModel();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel userSaved = userRepo.save(newUser);

        ResponseDTO response = new ResponseDTO();
        response.setEmail(userSaved.getEmail());
        response.setLastName(userSaved.getLastName());
        response.setFirstName(user.getFirstName());
        return response;
    }

}
