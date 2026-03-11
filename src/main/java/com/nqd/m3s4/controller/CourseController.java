package com.nqd.m3s4.controller;

import com.nqd.m3s4.dto.CourseCreateRequest;
import com.nqd.m3s4.dto.CourseUpdateRequest;
import com.nqd.m3s4.response.ApiResponse;
import com.nqd.m3s4.response.CourseResponse;
import com.nqd.m3s4.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCourse(@RequestBody CourseCreateRequest req) {

        courseService.createCourse(req);

        return ResponseEntity.ok(
                new ApiResponse<>("Course created successfully", null)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseUpdateRequest req) {

        courseService.updateCourse(id, req);

        return ResponseEntity.ok(
                new ApiResponse<>("Course updated successfully", null)
        );
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
