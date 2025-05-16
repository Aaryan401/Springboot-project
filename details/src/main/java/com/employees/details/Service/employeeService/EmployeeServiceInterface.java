package com.employees.details.Service.employeeService;

import com.employees.details.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInterface
{
    public Employee saveEmployee(Employee employee);
    public Optional<Employee> updateEmployeeDetails(Long empId, Employee employee);
    public String deleteEmployee(Long empId);
    public Optional<Employee> getEmployeeById(Long empId);
    public List<Employee> getAllEmployee();
    public Employee getEmployeeByEmail(String email);
    public List<Employee> getEmployeeByfirstNameAndSalary(String firstName, Long salary);
}
