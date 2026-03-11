package com.nqd.m3s4.controller;

import com.nqd.m3s4.dto.InstructorCreateRequest;
import com.nqd.m3s4.response.ApiResponse;
import com.nqd.m3s4.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createInstructor(@RequestBody InstructorCreateRequest req) {

        instructorService.createInstructor(req);

        return ResponseEntity.ok(
                new ApiResponse<>("Instructor created successfully", null)
        );
    }
}
