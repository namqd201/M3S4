package com.nqd.m3s4.response;

import com.nqd.m3s4.model.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;

    public CourseResponse(Long id,
                          String title,
                          CourseStatus status,
                          CourseInstructorResponse instructor) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructor = instructor;
    }
}
