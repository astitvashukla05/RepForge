package com.RepForge.user_service.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "First name is required")
    @Size(max = 40, min = 3, message = "Minimum character required : 3")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 40, min = 3, message = "Minimum character required : 3")
    private String lastName;

    @NotBlank(message = "Please enter a valid email")
    @Email(message = "Valid email format required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Minimum character required : 6 ")
    private String password;
}
