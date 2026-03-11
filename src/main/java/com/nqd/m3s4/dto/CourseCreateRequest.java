package com.nqd.m3s4.dto;

import com.nqd.m3s4.model.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
