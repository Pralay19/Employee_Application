package com.employee.employee_crud;

import com.employee.employee_crud.repo.DepartmentRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EmployeeCrudApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeCrudApplication.class, args);
	}

}
