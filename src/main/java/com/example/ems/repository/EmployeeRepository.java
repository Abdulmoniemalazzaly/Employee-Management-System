package com.example.ems.repository;

import com.example.ems.entity.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.Collection;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    long countByDepartmentId(Long departmentId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Employee e where e.id in :ids")
    int deleteByIds(@Param("ids") Collection<Long> ids);
}
