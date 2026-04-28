package com.example.ems.mapper;

import com.example.ems.dto.EmployeeResponseDto;
import com.example.ems.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    EmployeeResponseDto toResponse(Employee employee);
}
