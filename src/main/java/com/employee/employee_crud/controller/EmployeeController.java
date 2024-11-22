package com.employee.employee_crud.controller;


import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")

public class EmployeeController {
    private final EmployeeService employeeService;



    //REGISTRATION
    @PostMapping("/register")
    public void register(@RequestBody EmployeeRequest request) {
        Employee employee = employeeService.registerEmployee(request);

    }

    //UPDATE
    @PostMapping("/updateinfo")
    public void updateinfo(@RequestBody EmployeeRequest request,HttpServletRequest req) {
        Employee employee = employeeService.updateEmployee(request,req);

    }


}
