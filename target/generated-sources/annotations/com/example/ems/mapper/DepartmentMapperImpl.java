package com.example.ems.mapper;

import com.example.ems.dto.DepartmentResponseDto;
import com.example.ems.entity.Department;
import com.example.ems.entity.DepartmentStatus;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-29T00:37:06+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentResponseDto toResponse(Department department) {
        if ( department == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        DepartmentStatus status = null;
        Instant createdAt = null;
        Instant updatedAt = null;
        Long version = null;

        id = department.getId();
        name = department.getName();
        description = department.getDescription();
        status = department.getStatus();
        createdAt = department.getCreatedAt();
        updatedAt = department.getUpdatedAt();
        version = department.getVersion();

        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto( id, name, description, status, createdAt, updatedAt, version );

        return departmentResponseDto;
    }
}
