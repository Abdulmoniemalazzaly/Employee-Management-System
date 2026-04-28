package com.example.ems.dto;

import com.example.ems.entity.EmployeeStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeePatchRequestDto(
        @Size(max = 80) String firstName,
        @Size(max = 80) String lastName,
        @Email @Size(max = 160) String email,
        @PastOrPresent LocalDate hireDate,
        @DecimalMin("0.00") BigDecimal salary,
        EmployeeStatus status,
        @Size(max = 100) String jobTitle,
        Long departmentId
) {}
