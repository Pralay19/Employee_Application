package com.employee.employee_crud.mapper;


import com.employee.employee_crud.dto.ProfileResponse;
import com.employee.employee_crud.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class ProfileMapper {
    public ProfileResponse toProfileResponse(Employee employee) {
        return ProfileResponse.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .title(employee.getTitle())
                .photoPath(employee.getPhotoPath())
                .department(employee.getDepartment().getName())
                .build();
    }
}
