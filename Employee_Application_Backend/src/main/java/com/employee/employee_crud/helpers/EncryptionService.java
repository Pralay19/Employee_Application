package com.employee.employee_crud.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor

public class EncryptionService {
    private final PasswordEncoder passwordEncoder;
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
    public boolean validates(String password, String encodedPassword) {
//        System.out.println(password+"-------"+encodedPassword);
        return passwordEncoder.matches(password, encodedPassword);
    }
}
