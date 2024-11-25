package com.employee.employee_crud.controller;


import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.dto.ProfileResponse;
import com.employee.employee_crud.entity.Employee;
import com.employee.employee_crud.helpers.GeneratePhotoPath;
import com.employee.employee_crud.helpers.JWTHelper;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/iiitb/Employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final GeneratePhotoPath generatepp;
    private final ProfileMapper profileMapper;
    private final JWTHelper jwtHelper;

    //GET USER
    @GetMapping("/profile/{email}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String email,HttpServletRequest req) {
        if(!jwtHelper.validateAuthorizationHeader(req.getHeader("Authorization"))){
            return null;//INCORRECT TOKEN EXCEPTION TO BE ADDED LATER
        }
        Employee employee = employeeService.getEmployee(email);
        ProfileResponse profileResponse = profileMapper.toProfileResponse(employee);
        return ResponseEntity.ok(profileResponse);
    }



    //REGISTRATION
    @PostMapping(consumes = "multipart/form-data", path = "/register")
    public Map<String,String> register(@RequestPart(name="photo",required = false) MultipartFile photo, @RequestPart("data") EmployeeRequest request) {


        Random rand = new Random();
        String photoname = String.valueOf(1000+rand.nextInt(1000));
        String photoPath = "";
        if(photo!=null)  photoPath = generatepp.savePhotograph(photo,photoname);

        Employee employee = employeeService.registerEmployee(request,photoPath);
        Map<String,String> response = new HashMap<>();
        response.put("email",employee.getEmail());
//        return employee.getEmail();
        return response;
    }

    //UPDATE
    @PostMapping("/Profile/updateinfo")
    public void updateinfo(@RequestPart(name="photo",required = false) MultipartFile photo,@RequestPart("data") EmployeeRequest request,HttpServletRequest req) {

        Random rand = new Random();
        String photoname = String.valueOf(1000+rand.nextInt());
        String photoPath = "";
        if(photo!=null)  photoPath = generatepp.savePhotograph(photo,photoname);
        Employee employee = employeeService.updateEmployee(request,req,photoPath);

    }


}
