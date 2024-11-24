package com.employee.employee_crud.dto;

import com.employee.employee_crud.entity.Departments;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProfileResponse(
        @NotBlank(message = "First name is required")
        @NotEmpty
        @NotNull
        String firstName,

        String lastName,

        @NotBlank
        @NotNull
        @NotEmpty
        @Email(message = "Email must be in correct format")
        String email,

        String password,

        String title,

        String employeeId,

        String photoPath,

        String department
) {
}
