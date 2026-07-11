package com.RepForge.user_service.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.RepForge.user_service.model.ApiResponse;
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

    public ApiResponse getUserById(String user_id) {
        ResponseDTO user = userRepo.findByUUID(user_id).get();
        if (user == null) {
            return new ApiResponse(false, "User Not Found", user, null, HttpStatus.NOT_FOUND);
        }
        return new ApiResponse(true, "User Found", user, null, HttpStatus.OK);
    }

    public ApiResponse registerUser(RegisterDTO user) {

        if (user.getEmail() == null)
            return new ApiResponse(false, "Enter valid email", null, user, HttpStatus.BAD_REQUEST);
        if (user.getFirstName() == null)
            return new ApiResponse(false, "Enter valid first name", null, user, HttpStatus.BAD_REQUEST);
        if (user.getLastName() == null)
            return new ApiResponse(false, "Enter valid last name", null, user, HttpStatus.BAD_REQUEST);
        if (user.getPassword() == null)
            return new ApiResponse(false, "Enter valid password", null, user, HttpStatus.BAD_REQUEST);

        ResponseDTO exsistingUser = userRepo.findByEmail(user.getEmail()).get();

        if (exsistingUser != null) {
            return new ApiResponse(false, "User Already Exsists", null, user, HttpStatus.BAD_REQUEST);
        }

        UserModel newUser = new UserModel();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());

        UserModel userSaved = userRepo.save(newUser);

        if (userSaved == null) {
            return new ApiResponse(false, "User Not Saved", null, user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ApiResponse(true, "User Registered Successfully", null, user, HttpStatus.CREATED);
    }

}
