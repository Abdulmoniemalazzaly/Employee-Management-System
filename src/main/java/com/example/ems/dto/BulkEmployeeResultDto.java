package com.example.ems.dto;

import java.util.List;

public record BulkEmployeeResultDto(int requested, int created, List<String> errors) {}

