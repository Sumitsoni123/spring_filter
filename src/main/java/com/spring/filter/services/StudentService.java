package com.spring.filter.services;

import com.spring.filter.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student createStudent(Student student) {
        // Logic to create a student
        Student std = new Student();
        std.setName(student.getName());
        std.setEmail(student.getEmail());
        return std;
    }
}
