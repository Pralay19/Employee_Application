package com.employee.employee_crud.dto;

import com.employee.employee_crud.entity.Department;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record EmployeeRequest(

        @NotBlank(message = "First name is required")
        @NotEmpty
        @NotNull
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotBlank
        @NotNull
        @NotEmpty
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @JsonProperty("last_name")
        String title,

        @JsonProperty("photo_path")
        String photoPath,

        @JsonProperty("department")
        Department department,

        @NotNull(message = "Password is required")
        @NotEmpty(message = "Password is required")
        @NotBlank(message = "Password is required")
        @Size(min = 10, max = 10, message = "Password must be of 10 Characters")
        @JsonProperty("password")
        String password,

        @NotNull
        @NotEmpty
        @NotBlank
        @JsonProperty("access_token")
        String access_token

) {
}
