package com.employee.employee_crud.dto;

import com.employee.employee_crud.entity.Departments;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;


public record EmployeeRequest(

        @NotBlank(message = "First name is required")
        @NotEmpty
        @NotNull
//        @JsonProperty("first_name")
        String firstName,

//        @JsonProperty("last_name")
        String lastName,

        @NotBlank
        @NotNull
        @NotEmpty
        @Email(message = "Email must be in correct format")
//        @JsonProperty("email")
        String email,

//        @JsonProperty("title")
        String title,


        String employeeId,

//        @JsonProperty("photo_path")
        String photoPath,

//        @JsonProperty("department")
        Departments department,

        @NotEmpty(message = "Password is required")
        @NotBlank(message = "Password is required")
        @Size(min = 5, max = 20, message = "Password must be of 5 to 20 Characters")
//        @JsonProperty("password")
        String password

) {
}
