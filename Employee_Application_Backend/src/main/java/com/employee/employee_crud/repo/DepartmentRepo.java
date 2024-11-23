package com.employee.employee_crud.repo;

import com.employee.employee_crud.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Departments,Integer> {
//    Optional<Departments> findById(int depid);
    Optional<Departments> findByName(String name);
}
