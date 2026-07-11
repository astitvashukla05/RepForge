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
    @Size(max = 40, min = 6, message = "Enter Valid Name")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(max = 40, min = 6, message = "Enter Valid Name")
    private String lastName;
    @NotBlank(message = "Please enter a valid email")
    @Email(message = "Enter a valid email")
    private String email;
    @NotBlank(message = "Please enter a valid passwrod")
    @Size(min = 6, message = "Minimum 6 characters required")
    private String password;
}
