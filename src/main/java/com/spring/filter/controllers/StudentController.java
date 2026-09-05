package com.spring.filter.controllers;

import com.spring.filter.dto.Student;
import com.spring.filter.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok("Student created successfully");
    }
}
