
package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByNameContaining(String name);

    @Query("SELECT e.name AS name FROM Employee e")
    List<EmployeeNameProjection> findAllEmployeeNames();

    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findEmployeesByDepartment(@Param("departmentId") Long departmentId);

    @Query("SELECT COUNT(e) FROM Employee e")
    long countTotalEmployees();
}
