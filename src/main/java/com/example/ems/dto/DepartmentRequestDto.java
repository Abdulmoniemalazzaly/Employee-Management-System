package com.example.ems.dto;

import com.example.ems.entity.DepartmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentRequestDto(
        @NotBlank @Size(max = 120) String name,
        @Size(max = 500) String description,
        @NotNull DepartmentStatus status
) {}
