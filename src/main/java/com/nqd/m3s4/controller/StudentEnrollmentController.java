package com.nqd.m3s4.controller;

import com.nqd.m3s4.dto.CourseEnrollmentRequest;
import com.nqd.m3s4.response.ApiResponse;
import com.nqd.m3s4.response.CourseEnrollmentResponse;
import com.nqd.m3s4.service.StudentEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students-enrollments")
public class StudentEnrollmentController {

    private final StudentEnrollmentService enrollmentService;

    public StudentEnrollmentController(StudentEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/courses/{courseId}/enrollments")
    public ResponseEntity<CourseEnrollmentResponse> enrollStudent(
            @PathVariable Long courseId,
            @RequestBody CourseEnrollmentRequest req) {

        return ResponseEntity.ok(
                enrollmentService.enrollStudent(courseId, req)
        );
    }

    @DeleteMapping("/courses/{courseId}/enrollments/students/{studentId}")
    public ResponseEntity<Void> dropStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {

        enrollmentService.dropStudent(courseId, studentId);

        return ResponseEntity.noContent().build();
    }

}
