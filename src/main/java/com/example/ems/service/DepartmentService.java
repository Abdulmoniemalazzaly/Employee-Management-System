package com.example.ems.service;

import com.example.ems.dto.DepartmentRequestDto;
import com.example.ems.dto.DepartmentResponseDto;
import com.example.ems.entity.Department;
import com.example.ems.mapper.DepartmentMapper;
import com.example.ems.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departments;
    private final EmployeeRepository employees;
    private final DepartmentMapper departmentMapper;

    @Transactional(readOnly = true)
    public Page<DepartmentResponseDto> list(Pageable pageable) {
        return departments.findAll(pageable).map(departmentMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public DepartmentResponseDto get(Long id) {
        return departmentMapper.toResponse(find(id));
    }

    @Transactional
    public DepartmentResponseDto create(DepartmentRequestDto request) {
        if (departments.existsByNameIgnoreCase(request.name())) throw new IllegalArgumentException("Department name already exists");
        Department d = Department.builder().name(request.name().trim()).description(request.description()).status(request.status()).build();
        return departmentMapper.toResponse(departments.save(d));
    }

    @Transactional
    public DepartmentResponseDto update(Long id, DepartmentRequestDto request) {
        Department d = find(id);
        d.setName(request.name().trim());
        d.setDescription(request.description());
        d.setStatus(request.status());
        return departmentMapper.toResponse(d);
    }

    @Transactional
    public void delete(Long id) {
        if (employees.countByDepartmentId(id) > 0) throw new IllegalArgumentException("Cannot delete department with employees");
        departments.delete(find(id));
    }

    Department find(Long id) {
        return departments.findById(id).orElseThrow(() -> new EntityNotFoundException("Department not found: " + id));
    }
}
