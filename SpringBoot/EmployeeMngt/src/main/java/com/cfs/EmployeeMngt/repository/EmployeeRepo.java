package com.cfs.EmployeeMngt.repository;

import com.cfs.EmployeeMngt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
