package com.taller.bookstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "name must not be blank")
    private String name;

    @Email(message = "email must be valid")
    @NotBlank(message = "email must not be blank")
    private String email;

    @Size(min = 8, message = "password must have at least 8 characters")
    private String password;
}