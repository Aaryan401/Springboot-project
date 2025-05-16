package com.employees.details.Repository;

import com.employees.details.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public Employee findByEmail(String email);

    public List<Employee> findAllByFirstNameAndSalary(String firstName, Long salary);
}
