package com.employee.employee_crud.Exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    private final String msg;
}
