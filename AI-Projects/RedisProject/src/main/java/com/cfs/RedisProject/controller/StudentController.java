package com.cfs.RedisProject.controller;


import com.cfs.RedisProject.entity.Student;
import com.cfs.RedisProject.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCheckpointRestore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name="Student API",description = "CURD operations for students")
public class StudentController {

    private final StudentService service;


    public StudentController(StudentService service) {
        this.service = service;
    }

    @Operation(summary = "create a new student",description = "create a new student in redis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "student creates successfully")
    }
    )
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) throws InterruptedException {

        System.out.println("Student id"+student.getId());
        Student res = service.createStudent(student);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All students",description = "get all student record from redis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List of students")
    }
    )
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws InterruptedException {
       List<Student> students=service.getAllStudents();
       return ResponseEntity.ok(students);
    }

    @Operation(summary = "search by email",description = "find student in redis by email")
    @GetMapping("/search/email")
    public ResponseEntity<List<Student>> getStudentByEmail(@RequestParam String email) throws InterruptedException {
        List<Student> students=service.getStudentsByEmail(email);
        return ResponseEntity.ok(students);
    }


    @Operation(summary = "delete All students",description = "delete all student record from redis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "All students deleted")
    }
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudent() throws InterruptedException {
        service.deleteAllStudents();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "delet student by id",description = "delete student record by id from redis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "All students deleted"),
            @ApiResponse(responseCode = "404",description = "students not found ")
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) throws InterruptedException {
        service.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
