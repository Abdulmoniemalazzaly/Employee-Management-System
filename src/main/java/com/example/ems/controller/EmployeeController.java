package com.example.ems.controller;

import com.example.ems.dto.*;
import com.example.ems.entity.EmployeeStatus;
import com.example.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public Page<EmployeeResponseDto> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) EmployeeStatus status,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredTo,
            @PageableDefault(size = 20, sort = "hireDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(q, status, departmentId, hiredFrom, hiredTo, pageable);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PatchMapping("/{id}")
    public EmployeeResponseDto patch(@PathVariable Long id, @Valid @RequestBody EmployeePatchRequestDto request) { return service.patch(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }

    @PostMapping("/bulk")
    public ResponseEntity<BulkEmployeeResultDto> bulkCreate(@Valid @RequestBody BulkEmployeeRequestDto request) {
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(service.bulkCreate(request));
    }
}
