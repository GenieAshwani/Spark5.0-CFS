package com.example.demo.controller;


import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/department/{departmentId}")
    public Employee saveEmployee(@PathVariable Integer departmentId, @RequestBody Employee employee)
    {
        return employeeService.saveEmployee(departmentId,employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public List<Employee> getEmployeeById(@PathVariable Integer id)
    {
        return employeeService.getAllEmployees();
    }


}
