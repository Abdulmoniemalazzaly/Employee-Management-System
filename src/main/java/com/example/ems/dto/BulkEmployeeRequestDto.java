package com.example.ems.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record BulkEmployeeRequestDto(@NotEmpty @Size(max = 500) List<@Valid EmployeeRequestDto> employees) {}
