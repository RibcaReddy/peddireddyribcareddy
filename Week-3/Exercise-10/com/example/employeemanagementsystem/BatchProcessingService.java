
package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class BatchProcessingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveAllEmployees(List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));
            if (i % 50 == 0) { // Flush and clear every 50 entities
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
