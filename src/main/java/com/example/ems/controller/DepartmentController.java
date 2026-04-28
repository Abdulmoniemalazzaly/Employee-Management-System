package com.example.ems.controller;

import com.example.ems.dto.DepartmentRequestDto;
import com.example.ems.dto.DepartmentResponseDto;
import com.example.ems.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping
    public Page<DepartmentResponseDto> list(@PageableDefault(size = 20, sort = "name") Pageable pageable) { return service.list(pageable); }

    @GetMapping("/{id}")
    public DepartmentResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> create(@Valid @RequestBody DepartmentRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public DepartmentResponseDto update(@PathVariable Long id, @Valid @RequestBody DepartmentRequestDto request) { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
