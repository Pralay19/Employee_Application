package com.employee.employee_crud.services;

import com.employee.employee_crud.Exceptions.EmployeeNotFoundException;
import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.dto.LoginRequest;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.helpers.EncryptionService;
import com.employee.employee_crud.helpers.JWTHelper;
import com.employee.employee_crud.mapper.EmployeeMapper;
import com.employee.employee_crud.repo.EmployeeRepo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class EmployeeService {
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    private final EmployeeRepo repo;
    private final EmployeeMapper mapper;

    public Employee getEmployee(@NotBlank @Email String email) {
        return repo.findByEmail(email)
                .orElseThrow(()->new EmployeeNotFoundException(
                        format("No Employee found with provided email: %s",email)
                ));
    }

    @PostMapping("/register")
    public Employee registerEmployee(@RequestBody EmployeeRequest request){
        Employee employee = mapper.toEmployee(request);
        employee.setPassword(encryptionService.encode(request.password()));
        repo.save(employee);
        return employee;
    }


    public String loginEmployee(LoginRequest request) {
        Employee employee = getEmployee(request.email());
        if(!encryptionService.validates(request.password(), employee.getPassword())){
            return "Invalid Credentials";
        }
        return jwtHelper.generateToken(request.email());
    }
}
