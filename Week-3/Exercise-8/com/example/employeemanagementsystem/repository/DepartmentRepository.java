
package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d.name AS name FROM Department d")
    List<DepartmentNameProjection> findAllDepartmentNames();

    Optional<Department> findByName(String name);

    @Query("SELECT d FROM Department d WHERE d.name LIKE :prefix%")
    List<Department> findDepartmentsStartingWith(@Param("prefix") String prefix);
}
