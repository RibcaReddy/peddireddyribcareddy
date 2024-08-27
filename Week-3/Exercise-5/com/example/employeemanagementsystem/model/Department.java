
package com.example.employeemanagementsystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // Constructors, Getters, Setters, and toString method
}
