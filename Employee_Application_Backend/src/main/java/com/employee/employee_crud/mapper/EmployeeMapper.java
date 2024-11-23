package com.employee.employee_crud.mapper;

import com.employee.employee_crud.dto.EmployeeRequest;
import com.employee.employee_crud.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employee toEmployee(EmployeeRequest request){
        return Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .title(request.title())
//                .photoPath(request.photoPath())
//                .department(request.department())
                .password(request.password())
                .build();
    }
}
