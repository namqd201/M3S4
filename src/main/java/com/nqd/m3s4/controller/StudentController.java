package com.nqd.m3s4.controller;

import com.nqd.m3s4.model.Student;
import com.nqd.m3s4.repository.StudentRepository;
import com.nqd.m3s4.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createStudent(@RequestBody Student student) {

        studentRepository.save(student);

        return ResponseEntity.ok(
                new ApiResponse<>("Student created successfully", null)
        );
    }

    @GetMapping("/courses/{courseId}/enrollments/students")
    public ResponseEntity<List<Student>> searchStudents(
            @PathVariable Long courseId,
            @RequestParam String search) {

        return ResponseEntity.ok(
                studentRepository.searchStudents(courseId, search)
        );
    }
}