package com.cfs.EmployeeMngt.service;

import com.cfs.EmployeeMngt.entity.Employee;
import com.cfs.EmployeeMngt.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found by id = "+id));
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee exsitingEmployee=employeeRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found by id = "+id));

        exsitingEmployee.setName(employee.getName());
        exsitingEmployee.setEmail(employee.getEmail());
        exsitingEmployee.setDepartment(employee.getDepartment());
        exsitingEmployee.setSalary(employee.getSalary());

        return employeeRepo.save(exsitingEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee exsitingEmployee=employeeRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found by id = "+id));
        employeeRepo.delete(exsitingEmployee);
    }
}
