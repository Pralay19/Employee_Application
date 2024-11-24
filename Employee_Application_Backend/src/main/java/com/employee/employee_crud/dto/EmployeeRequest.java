package com.employee.employee_crud.dto;

import com.employee.employee_crud.entity.Departments;
import jakarta.validation.constraints.*;



public record EmployeeRequest(

        @NotBlank(message = "First name is required")
        @NotEmpty
        @NotNull
        String firstName,

        String lastName,

//        @NotBlank
//        @NotNull
//        @NotEmpty
//        @Email(message = "Email must be in correct format")
        String email,

        String title,

        String employeeId,

        String photoPath,

        String department,

        @NotEmpty(message = "Password is required")
        @NotBlank(message = "Password is required")
        @Size(min = 5, max = 20, message = "Password must be of 5 to 20 Characters")
        String password

) {
}
