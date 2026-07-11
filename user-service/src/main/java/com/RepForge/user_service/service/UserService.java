package com.RepForge.user_service.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.RepForge.user_service.model.UserModel;
import com.RepForge.user_service.model.DTOs.RegisterDTO;
import com.RepForge.user_service.model.DTOs.ResponseDTO;
import com.RepForge.user_service.repository.UserRepo;

@Service
public class UserService {

    UserRepo userRepo;

    UserService(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    public Optional<ResponseDTO> getUserById(UUID user_id) {
        Optional<UserModel> optionalUser = userRepo.findById(user_id);
        if (optionalUser.isEmpty())
            return Optional.empty();
        UserModel user = optionalUser.get();
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setEmail(user.getEmail());
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        return Optional.of(responseDTO);
    }

    public Optional<ResponseDTO> registerUser(RegisterDTO user) {

        Optional<UserModel> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }
        UserModel newUser = new UserModel();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());

        UserModel userSaved = userRepo.save(newUser);

        ResponseDTO response = new ResponseDTO();
        response.setEmail(userSaved.getEmail());
        response.setLastName(userSaved.getLastName());
        response.setFirstName(user.getFirstName());
        return Optional.of(response);
    }

}
