package com.example.ems.dto;

import com.example.ems.entity.EmployeeStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeRequestDto(
        @NotBlank @Size(max = 80) String firstName,
        @NotBlank @Size(max = 80) String lastName,
        @NotBlank @Email @Size(max = 160) String email,
        @NotNull @PastOrPresent LocalDate hireDate,
        @NotNull @DecimalMin("0.00") BigDecimal salary,
        @NotNull EmployeeStatus status,
        @Size(max = 100) String jobTitle,
        @NotNull Long departmentId
) {}
