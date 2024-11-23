package com.employee.employee_crud.controller;


import com.employee.employee_crud.dto.LoginRequest;
import com.employee.employee_crud.helpers.JWTHelper;
import com.employee.employee_crud.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/iiitb/Employee")
public class AuthenticationController {
    private final EmployeeService employeeService;
    private final JWTHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        try {
            String token = employeeService.loginEmployee(request);
            String email = jwtHelper.extractEmail(token);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/iiitb/Employee/profile/" + email));
            headers.set("Authorization", "Bearer " + token);

            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }
}
