package com.employee.employee_crud.Exceptions;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidCredentialsException extends RuntimeException{
    private final String msg;
}
