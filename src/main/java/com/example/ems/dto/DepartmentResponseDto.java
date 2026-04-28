package com.example.ems.dto;

import com.example.ems.entity.DepartmentStatus;

import java.time.Instant;

public record DepartmentResponseDto(
        Long id, String name, String description, DepartmentStatus status,
        Instant createdAt, Instant updatedAt, Long version
) {}
