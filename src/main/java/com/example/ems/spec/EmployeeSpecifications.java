package com.example.ems.spec;

import com.example.ems.entity.*;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public final class EmployeeSpecifications {
    private EmployeeSpecifications() {}

    public static Specification<Employee> filter(String q, EmployeeStatus status, Long departmentId, LocalDate hiredFrom, LocalDate hiredTo) {
        return Specification.where(search(q)).and(status(status)).and(department(departmentId)).and(hiredFrom(hiredFrom)).and(hiredTo(hiredTo));
    }

    private static Specification<Employee> search(String q) {
        return (root, query, cb) -> {
            if (q == null || q.isBlank()) return cb.conjunction();
            String like = "%" + q.toLowerCase() + "%";
            return cb.or(cb.like(cb.lower(root.get("firstName")), like), cb.like(cb.lower(root.get("lastName")), like), cb.like(cb.lower(root.get("email")), like));
        };
    }
    private static Specification<Employee> status(EmployeeStatus status) { return (r,q,cb) -> status == null ? cb.conjunction() : cb.equal(r.get("status"), status); }
    private static Specification<Employee> department(Long id) { return (r,q,cb) -> id == null ? cb.conjunction() : cb.equal(r.get("department").get("id"), id); }
    private static Specification<Employee> hiredFrom(LocalDate d) { return (r,q,cb) -> d == null ? cb.conjunction() : cb.greaterThanOrEqualTo(r.get("hireDate"), d); }
    private static Specification<Employee> hiredTo(LocalDate d) { return (r,q,cb) -> d == null ? cb.conjunction() : cb.lessThanOrEqualTo(r.get("hireDate"), d); }
}
