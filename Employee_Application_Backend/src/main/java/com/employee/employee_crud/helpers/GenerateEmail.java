package com.employee.employee_crud.helpers;

import com.employee.employee_crud.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class GenerateEmail {

    private final EmployeeRepo employeeRepo;

    public String generateUniqueEmail(String firstName, String lastName) {
        String baseEmail = lastName + "." + firstName;
        String emailDomain = "iiitb.ac.in";
        String email = baseEmail + "@" + emailDomain;

        List<String> existingEmails = employeeRepo.findEmailsWithPrefix(baseEmail);
        if (!existingEmails.contains(email)) {
            return email;
        }

        Random random = new Random();
        int attempt = 0;
        String uniqueEmail;
        do {
            int randomNum = 100 + random.nextInt(900); // Random 3-digit number
            uniqueEmail = baseEmail + randomNum + "@" + emailDomain;
            attempt++;
        } while (existingEmails.contains(uniqueEmail) && attempt < 100);

        if (attempt == 100) {
            throw new IllegalStateException("Unable to generate a unique email. Please contact administrator.");
        }

        return uniqueEmail;
    }
}
