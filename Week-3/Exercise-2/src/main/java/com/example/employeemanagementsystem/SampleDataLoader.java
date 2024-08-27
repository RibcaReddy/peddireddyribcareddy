package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader {

    @Bean
    CommandLineRunner init(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        return args -> {
            Department department = new Department();
            department.setName("Engineering");
            departmentRepository.save(department);

            Employee employee = new Employee();
            employee.setName("John Doe");
            employee.setEmail("john.doe@example.com");
            employee.setDepartment(department);
            employeeRepository.save(employee);
        };
    }
}
