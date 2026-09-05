package com.spring.filter.services;

import com.spring.filter.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void createStudent(Student student) {
        // Logic to create a student
        System.out.println("Student Created");
        System.out.println(student.getName());
        System.out.println(student.getEmail());
    }
}
