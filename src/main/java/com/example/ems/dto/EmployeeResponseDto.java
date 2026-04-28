package com.example.ems.dto;

import com.example.ems.entity.EmployeeStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record EmployeeResponseDto(
        Long id, String firstName, String lastName, String email,
        LocalDate hireDate, BigDecimal salary, EmployeeStatus status,
        String jobTitle, Long departmentId, String departmentName,
        Instant createdAt, Instant updatedAt, Long version
) {}
