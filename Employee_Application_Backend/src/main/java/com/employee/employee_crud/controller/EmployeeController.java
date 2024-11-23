package com.employee.employee_crud.controller;


import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.dto.ProfileResponse;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.helpers.GeneratePhotoPath;
import com.employee.employee_crud.mapper.ProfileMapper;
import com.employee.employee_crud.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/iiitb/Employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final GeneratePhotoPath generatepp;
    private final ProfileMapper profileMapper;

    //GET USER
    @GetMapping("/profile/{email}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String email) {
        Employee employee = employeeService.getEmployee(email);
        ProfileResponse profileResponse = profileMapper.toProfileResponse(employee);
        return ResponseEntity.ok(profileResponse);
    }



    //REGISTRATION
    @PostMapping(consumes = "multipart/form-data", path = "/register")
    public Employee register(@RequestPart("photo") MultipartFile photo, @RequestPart("data")@Valid EmployeeRequest request) {


        Random rand = new Random();
        String photoname = String.valueOf(1000+rand.nextInt(1000));
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
