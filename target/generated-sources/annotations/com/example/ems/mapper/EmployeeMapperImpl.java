package com.example.ems.mapper;

import com.example.ems.dto.EmployeeResponseDto;
import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.entity.EmployeeStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-29T00:37:06+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeResponseDto toResponse(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        Long departmentId = null;
        String departmentName = null;
        Long id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        LocalDate hireDate = null;
        BigDecimal salary = null;
        EmployeeStatus status = null;
        String jobTitle = null;
        Instant createdAt = null;
        Instant updatedAt = null;
        Long version = null;

        departmentId = employeeDepartmentId( employee );
        departmentName = employeeDepartmentName( employee );
        id = employee.getId();
        firstName = employee.getFirstName();
        lastName = employee.getLastName();
        email = employee.getEmail();
        hireDate = employee.getHireDate();
        salary = employee.getSalary();
        status = employee.getStatus();
        jobTitle = employee.getJobTitle();
        createdAt = employee.getCreatedAt();
        updatedAt = employee.getUpdatedAt();
        version = employee.getVersion();

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto( id, firstName, lastName, email, hireDate, salary, status, jobTitle, departmentId, departmentName, createdAt, updatedAt, version );

        return employeeResponseDto;
    }

    private Long employeeDepartmentId(Employee employee) {
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department.getId();
    }

    private String employeeDepartmentName(Employee employee) {
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department.getName();
    }
}
