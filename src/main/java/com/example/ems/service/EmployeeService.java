package com.example.ems.service;

import com.example.ems.dto.*;
import com.example.ems.entity.*;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.spec.EmployeeSpecifications;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employees;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public Page<EmployeeResponseDto> list(String q, EmployeeStatus status, Long departmentId, LocalDate hiredFrom, LocalDate hiredTo, Pageable pageable) {
        return employees.findAll(EmployeeSpecifications.filter(q, status, departmentId, hiredFrom, hiredTo), pageable).map(employeeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto get(Long id) { return employeeMapper.toResponse(find(id)); }

    @Transactional
    public EmployeeResponseDto create(EmployeeRequestDto r) {
        if (employees.existsByEmailIgnoreCase(r.email())) throw new IllegalArgumentException("Employee email already exists");
        Employee e = newEmployee(r);
        return employeeMapper.toResponse(employees.save(e));
    }

    @Transactional
    public EmployeeResponseDto patch(Long id, @Valid EmployeePatchRequestDto r) {
        Employee e = find(id);
        validateAndApplyPatch(id, e, r);
        return employeeMapper.toResponse(e);
    }

    @Transactional
    public void delete(Long id) { employees.delete(find(id)); }

    @Transactional
    public BulkEmployeeResultDto bulkCreate(BulkEmployeeRequestDto request) {
        List<String> errors = new ArrayList<>();
        int created = 0;
        for (int i = 0; i < request.employees().size(); i++) {
            try {
                EmployeeRequestDto r = request.employees().get(i);
                if (employees.existsByEmailIgnoreCase(r.email())) throw new IllegalArgumentException("email exists: " + r.email());
                employees.save(newEmployee(r));
                created++;
            } catch (Exception ex) {
                errors.add("row " + i + ": " + ex.getMessage());
            }
        }
        return new BulkEmployeeResultDto(request.employees().size(), created, errors);
    }

    private Employee newEmployee(EmployeeRequestDto r) {
        return Employee.builder()
                .firstName(r.firstName().trim()).lastName(r.lastName().trim()).email(r.email().trim().toLowerCase())
                .hireDate(r.hireDate()).salary(r.salary()).status(r.status()).jobTitle(r.jobTitle())
                .department(departmentService.find(r.departmentId())).build();
    }

    private Employee find(Long id) {
        return employees.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));
    }

    private void validateAndApplyPatch(Long id, Employee e, EmployeePatchRequestDto r) {
        if (r.email() != null) {
            if (employees.existsByEmailIgnoreCaseAndIdNot(r.email(), id)) {
                throw new IllegalArgumentException("Employee email already exists");
            }
            e.setEmail(r.email().trim().toLowerCase());
        }
        if (r.firstName() != null) {
            e.setFirstName(r.firstName().trim());
        }
        if (r.lastName() != null) {
            e.setLastName(r.lastName().trim());
        }
        if (r.hireDate() != null) {
            e.setHireDate(r.hireDate());
        }
        if (r.salary() != null) {
            e.setSalary(r.salary());
        }
        if (r.status() != null) {
            e.setStatus(r.status());
        }
        if (r.jobTitle() != null) {
            e.setJobTitle(r.jobTitle());
        }
        if (r.departmentId() != null) {
            e.setDepartment(departmentService.find(r.departmentId()));
        }
    }
}
