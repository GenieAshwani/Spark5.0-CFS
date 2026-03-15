package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Employee saveEmployee(Integer id, Employee employee) {
        Department department=departmentRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Department not found with id = " +id));

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not found with id : "+id));
    }

    @Override
    @Transactional
    public Employee updateEmployee(Integer id, Employee employee) {

        Employee existingEmployee=employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not found with id : "+id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setSalary(employee.getSalary());

        if(employee.getDepartment()!=null && employee.getDepartment().getId()!=null)
        {
            Department department=departmentRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Department not found with id = "+id));

            existingEmployee.setDepartment(department);

        }

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {

    }
}
