package com.employee.employee_crud.services;

import com.employee.employee_crud.dto.LoginRequest;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.helpers.EncryptionService;
import com.employee.employee_crud.helpers.JWTHelper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmployeeService {
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String loginEmployee(LoginRequest request) {
        Employee employee = getEmployee(request.email());
        if(!encryptionService.validates(request.password(), employee.getPassword())){
            return "Invalid Credentials";
        }
        return jwtHelper.generateToken(request.email());
    }


}
