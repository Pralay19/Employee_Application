package com.employee.employee_crud.services;

import com.employee.employee_crud.Exceptions.DepartmentNotFoundException;
import com.employee.employee_crud.Exceptions.EmployeeNotFoundException;
import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.dto.LoginRequest;
import com.employee.employee_crud.entity.Departments;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.helpers.EmployeeIdGenerator;
import com.employee.employee_crud.helpers.EncryptionService;
import com.employee.employee_crud.helpers.GenerateEmail;
import com.employee.employee_crud.helpers.JWTHelper;
import com.employee.employee_crud.mapper.EmployeeMapper;
import com.employee.employee_crud.repo.DepartmentRepo;
import com.employee.employee_crud.repo.EmployeeRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor

public class EmployeeService {
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    private final EmployeeRepo repo;
    private final DepartmentRepo depo;
    private final EmployeeMapper mapper;
    private final EmployeeIdGenerator idGenerator;
    private final GenerateEmail generateEmail;

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

    public void checkDepartmentCapacity(@NotBlank String department) {
        Departments curDepartment = getDepartment(department);
        if(curDepartment.getCapacity()<(curDepartment.getCurrent_capacity()+1)){
            throw new IllegalArgumentException("Department capacity exceeds current capacity.");
        }
    }


    //REGISTRATION
    public Employee registerEmployee(EmployeeRequest request,String photoPath){
        //FIRST WE NEED TO CHECK CAPACITY
        checkDepartmentCapacity(request.department());
        Departments tempdep= depo.findByName(request.department())
                .orElseThrow(()->new DepartmentNotFoundException(
                        "No Department found with provided information."
                ));
        Employee employee = mapper.toEmployee(request);
        employee.setDepartment(tempdep);
        employee.setPassword(encryptionService.encode(request.password()));
        employee.setEmployeeId(idGenerator.generateUniqueEmployeeId());
        if(!Objects.equals(photoPath, "")) {
            employee.setPhotoPath(photoPath);
        }
        else {
            employee.setPhotoPath(null);
        }
        employee.setEmail(generateEmail.generateUniqueEmail(employee.getFirstName(),employee.getLastName()));
        repo.save(employee);
        tempdep.setCurrent_capacity(tempdep.getCurrent_capacity()+1);
        depo.save(tempdep);
        return employee;
    }



    //UPDATE
    public Employee updateEmployee(EmployeeRequest request, HttpServletRequest req,String photoPath){
        if(!jwtHelper.validateAuthorizationHeader(req.getHeader("Authorization"))){
            return null;//INCORRECT TOKEN EXCEPTION TO BE ADDED LATER
        }

        //FIRST CHECK IF ALREADY EXISTS
        Employee currentEmployee = getEmployee(request.email());
        if(!Objects.equals(currentEmployee.getEmployeeId(), request.employeeId())){
            checkIfEmployeeExistsByEmployeeId(request.employeeId());
        }
        else if (request.employeeId()== null || request.employeeId().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }

        checkDepartmentCapacity(request.department());
        Departments tempdep= depo.findByName(request.department())
                .orElseThrow(()->new DepartmentNotFoundException(
                        "No Department found with provided information."
                ));
        Employee employee = mapper.toEmployee(request);
        employee.setDepartment(tempdep);
        if(request.employeeId()!=null) {
            employee.setEmployeeId(request.employeeId());
        }
        if(!Objects.equals(photoPath, "") && !Objects.equals(currentEmployee.getPhotoPath(), photoPath)) {
            employee.setPhotoPath(photoPath);
        }
        else {
            employee.setPhotoPath(null);
        }
        employee.setPassword(encryptionService.encode(request.password()));
        employee.setId((currentEmployee.getId()));
        repo.save(employee);
        return employee;
    }

    //Login
    public String loginEmployee(LoginRequest request) {
        Employee employee = getEmployee(request.email());
        if(!encryptionService.validates(request.password(), employee.getPassword())){
            return "Invalid Credentials";
        }
        return jwtHelper.generateToken(request.email());
    }
}
