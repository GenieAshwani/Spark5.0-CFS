package com.example.demo.controller;


import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public Department saveDepartment(@RequestBody Department department)
    {
        return service.saveDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments()
    {
        return service.getAllDepartment();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Integer id)
    {
        return service.getDepartmentById(id);
    }

}
