package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "employees", indexes = {
        @Index(name = "idx_employees_department", columnList = "department_id"),
        @Index(name = "idx_employees_status", columnList = "status"),
        @Index(name = "idx_employees_hire_date", columnList = "hire_date")
})
public class Employee extends BaseEntity {
    @Column(nullable = false, length = 80)
    private String firstName;

    @Column(nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @Column(nullable = false, name = "hire_date")
    private LocalDate hireDate;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EmployeeStatus status;

    @Column(length = 100)
    private String jobTitle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
