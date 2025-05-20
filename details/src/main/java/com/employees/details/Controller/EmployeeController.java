package com.employees.details.Controller;

import com.employees.details.Entity.Employee;
import com.employees.details.Service.employeeService.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeServiceImp employeeService;

    public EmployeeController(EmployeeServiceImp employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> emp=employeeService.getAllEmployee();
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(name="id") Long empId){
        Optional<Employee> optionalEmployee=employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(optionalEmployee,HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee employee1=employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employee1,HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable(name="id") Long empId, @RequestBody Employee employee){
        Optional<Employee> employee1= employeeService.updateEmployeeDetails(empId,employee);
        return new ResponseEntity<>(employee1, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name="id") Long empId){
       String response= employeeService.deleteEmployee(empId);
       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<Employee> getByEmail(@RequestParam(name="email", required = false, defaultValue="aaryan14@gmail.com") String email){
        Employee emp=employeeService.getEmployeeByEmail(email);
        return new ResponseEntity<>(emp,HttpStatus.OK);
        }

    @GetMapping("searching")
        public ResponseEntity<List<Employee>> getDetailsByNameAndSalary(
                @RequestParam(name="FirstName", required=false, defaultValue ="Aaryan") String firstName,
                @RequestParam(name="Salary", required =false, defaultValue ="25000") Long salary
                        ){
        List<Employee> employees=employeeService.getEmployeeByfirstNameAndSalary(firstName,salary);
        return new ResponseEntity<>(employees,HttpStatus.OK);
        }
}

