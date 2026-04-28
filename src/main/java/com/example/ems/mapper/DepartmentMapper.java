package com.example.ems.mapper;

import com.example.ems.dto.DepartmentResponseDto;
import com.example.ems.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponseDto toResponse(Department department);
}
