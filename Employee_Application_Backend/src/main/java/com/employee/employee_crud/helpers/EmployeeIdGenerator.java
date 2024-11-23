package com.employee.employee_crud.helpers;

import com.employee.employee_crud.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmployeeIdGenerator {
    private final EmployeeRepo repo;

    public String generateUniqueEmployeeId() {
        String uniqueId;
        boolean isUnique = false;

        do {
            uniqueId = generateRandomId();
            isUnique = !repo.existsByEmployeeId(uniqueId);
        } while (!isUnique);

        return uniqueId;
    }

    private String generateRandomId() {
        return RandomStringUtils.randomAlphanumeric(15);
    }
}
