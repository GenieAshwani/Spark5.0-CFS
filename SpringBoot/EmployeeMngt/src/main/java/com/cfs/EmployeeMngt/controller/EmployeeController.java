package com.cfs.EmployeeMngt.controller;


import com.cfs.EmployeeMngt.entity.Employee;
import com.cfs.EmployeeMngt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getALlEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id)
    {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee)
    {
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id)
    {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}
