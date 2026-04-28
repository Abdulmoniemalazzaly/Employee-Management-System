package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "departments", indexes = {
        @Index(name = "idx_departments_status", columnList = "status")
})
public class Department extends BaseEntity {
    @Column(nullable = false, unique = true, length = 120)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private DepartmentStatus status;
}
