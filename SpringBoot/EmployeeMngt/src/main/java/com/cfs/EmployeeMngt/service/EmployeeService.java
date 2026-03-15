package com.cfs.EmployeeMngt.service;

import com.cfs.EmployeeMngt.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer id);
    Employee updateEmployee(Integer id,Employee employee);
    void deleteEmployee(Integer id);
}
