package com.employee.employee_crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Employee_id",unique = true, nullable = false)
    private String employeeId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "photograph_path")
    private String photoPath;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST) // Many employees can belong to one department
    @JoinColumn(name = "department", referencedColumnName = "depid")
    private Departments department;

    @Column(name="password", nullable = false)
    private String password;
}

