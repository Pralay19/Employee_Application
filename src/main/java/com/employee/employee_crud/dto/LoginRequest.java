package com.employee.employee_crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 10, max = 15, message = "Password must be at least 10 characters")
        @JsonProperty("password")
        String password
) {
}
