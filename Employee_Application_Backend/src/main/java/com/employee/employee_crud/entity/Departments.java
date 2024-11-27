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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depid;

    private String name;
    private int capacity;
    private int current_capacity;

}
