package com.employee.employee_crud.repo;

import com.employee.employee_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeId(String employeeId);
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
//    List<String> findByEmailIsStartingWith(String email);
@Query("SELECT email FROM Employee WHERE email LIKE :prefix%")
List<String> findEmailsWithPrefix(@Param("prefix") String prefix);

}
