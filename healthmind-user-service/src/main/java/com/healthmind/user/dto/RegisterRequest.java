package com.healthmind.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message =  "full_name is mandatory")
    private String fullName;

    @NotBlank(message = "email is mandatory")
    @Email(message = "Please Provide a valid email ID")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

}
