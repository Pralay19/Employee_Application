package com.employee.employee_crud.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int depid;

//    @Column(name = "name", unique=true)
    private String name;
    private int current_capacity;
    private int capacity;
}
