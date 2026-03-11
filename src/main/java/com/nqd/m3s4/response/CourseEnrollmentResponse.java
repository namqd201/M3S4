package com.nqd.m3s4.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseEnrollmentResponse {
    private Long studentId;
    private Long courseId;
    private LocalDateTime enrolledAt;

    public CourseEnrollmentResponse(Long studentId, Long courseId, LocalDateTime enrolledAt) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledAt = enrolledAt;
    }
}
