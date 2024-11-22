package com.employee.employee_crud.controller;


import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.helpers.GeneratePhotoPath;
import com.employee.employee_crud.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")

public class EmployeeController {
    private final EmployeeService employeeService;
    private final GeneratePhotoPath generatepp;

    //GET USER
    @GetMapping("/Employee/{employeeId}")
    public



    //REGISTRATION
    @PostMapping(consumes = "multipart/form-data", path = "/register")
    public Employee register(@RequestPart("photo") MultipartFile photo, @RequestPart("data")@Valid EmployeeRequest request) {


        Random rand = new Random();
        String photoname = String.valueOf(1000+rand.nextInt());
        String photoPath = "";
        if(photo!=null)  photoPath = generatepp.savePhotograph(photo,photoname);

        Employee employee = employeeService.registerEmployee(request,photoPath);

        return employee;
    }

    //UPDATE
    @PostMapping("/updateinfo")
    public void updateinfo(@RequestPart("photo") MultipartFile photo,@RequestPart("data") EmployeeRequest request,HttpServletRequest req) {

        Random rand = new Random();
        String photoname = String.valueOf(1000+rand.nextInt());
        String photoPath = "";
        if(photo!=null)  photoPath = generatepp.savePhotograph(photo,photoname);
        Employee employee = employeeService.updateEmployee(request,req,photoPath);

    }


}
