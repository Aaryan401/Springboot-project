package com.employees.details.Service.employeeService;

import com.employees.details.Entity.Employee;
import com.employees.details.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeServiceInterface{

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        Employee save= employeeRepository.save(employee);
        return save;
    }

    @Override
    public Optional<Employee> updateEmployeeDetails(Long empId, Employee employee){
        Optional<Employee> optionalEmployee=employeeRepository.findById(empId);
        if(optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setAge(employee.getAge());
            employeeRepository.save(existingEmployee);
        }
        return optionalEmployee;
    }

    @Override
    public String deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
        return "Employee "+empId+" has deleted";
    }

    @Override
    public Optional<Employee> getEmployeeById(Long empId) {
        Optional<Employee> optional =employeeRepository.findById(empId);
        return optional;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> emp =employeeRepository.findAll();
        return emp;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> getEmployeeByfirstNameAndSalary(String firstName, Long salary) {
        List<Employee> emp=employeeRepository.findAllByFirstNameAndSalary(firstName, salary);
        return emp;
    }

}
