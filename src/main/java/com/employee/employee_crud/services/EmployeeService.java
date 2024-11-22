package com.employee.employee_crud.services;

import com.employee.employee_crud.Exceptions.DepartmentNotFoundException;
import com.employee.employee_crud.Exceptions.EmployeeNotFoundException;
import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.dto.LoginRequest;
import com.employee.employee_crud.entity.Departments;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.helpers.EmployeeIdGenerator;
import com.employee.employee_crud.helpers.EncryptionService;
import com.employee.employee_crud.helpers.JWTHelper;
import com.employee.employee_crud.mapper.EmployeeMapper;
import com.employee.employee_crud.repo.DepartmentRepo;
import com.employee.employee_crud.repo.EmployeeRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")

public class EmployeeService {
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    private final EmployeeRepo repo;
    private final DepartmentRepo depo;
    private final EmployeeMapper mapper;
    private final EmployeeIdGenerator idGenerator;

    public Employee getEmployee(@NotBlank @Email String email) {
        return repo.findByEmail(email)
                .orElseThrow(()->new EmployeeNotFoundException(
                        format("No Employee found with provided email: %s",email)
                ));
    }
    public Departments getDepartment(@NotBlank String department) {
        return depo.findByName(department)
                .orElseThrow(()->new DepartmentNotFoundException(
                        "No Department found with provided information."
                ));
    }
//    public ResponseEntity<Employee> getEmployeeById(@NotBlank @Email String employeeId) {
//       Optional<Employee> employeeOptional=repo.findByEmployeeId(employeeId);
//       return employeeOptional.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.ok(new Employee()));
//    }

    public void checkIfEmployeeExistsByEmail(@NotBlank @Email String email) {
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("An employee with this email already exists.");
        }
    }
    public void checkIfEmployeeExistsByEmployeeId(@NotBlank @Email String email) {
        if (repo.existsByEmployeeId(email)) {
            throw new IllegalArgumentException("An employee with this EmployeeId already exists.");
        }
    }
    public void checkDepartmentCapacity(@NotBlank Departments department) {
        Departments curDepartment = getDepartment(department.getName());
        if(curDepartment.getCapacity()<(curDepartment.getCurrent_capacity()+1)){
            throw new IllegalArgumentException("Department capacity exceeds current capacity.");
        }
    }


    //REGISTRATION
    @PostMapping("/register")
    public Employee registerEmployee(@RequestBody EmployeeRequest request){
        //FIRST WE NEED TO CHECK IF SAME EMAIL EXITS
        checkIfEmployeeExistsByEmail(request.email());
        checkDepartmentCapacity(request.department());
        Departments tempdep= depo.findByName(request.department().getName())
                .orElseThrow(()->new DepartmentNotFoundException(
                        "No Department found with provided information."
                ));
        Employee employee = mapper.toEmployee(request);
        employee.setDepartment(tempdep);
        employee.setPassword(encryptionService.encode(request.password()));
        employee.setEmployeeId(idGenerator.generateUniqueEmployeeId());
        repo.save(employee);
        return employee;//TO BE MADE TO RETURN STRING ONLY LATER
    }



    //UPDATE
    @PostMapping("/updateinfo")
    public Employee updateEmployee(@RequestBody EmployeeRequest request, HttpServletRequest req){
        if(!jwtHelper.validateAuthorizationHeader(req.getHeader("Authorization"))){
            return null;//INCORRECT TOKEN EXCEPTION TO BE ADDED LATER
        }

        //FIRST CHECK IF ALREADY EXISTS
        Employee currentEmployee = getEmployee(request.email());
        if (request.employeeId()== null || request.employeeId().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        if(!Objects.equals(currentEmployee.getEmployeeId(), request.employeeId())){
            checkIfEmployeeExistsByEmployeeId(request.employeeId());
        }
        checkDepartmentCapacity(request.department());

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
