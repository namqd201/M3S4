package com.nqd.m3s4.service;

import com.nqd.m3s4.dto.CourseCreateRequest;
import com.nqd.m3s4.dto.CourseUpdateRequest;
import com.nqd.m3s4.model.Course;
import com.nqd.m3s4.model.Instructor;
import com.nqd.m3s4.repository.CourseRepository;
import com.nqd.m3s4.repository.InstructorRepository;
import com.nqd.m3s4.response.CourseInstructorResponse;
import com.nqd.m3s4.response.CourseResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository,
                         InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public void createCourse(CourseCreateRequest req) {

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public void updateCourse(Long id, CourseUpdateRequest req) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public List<CourseResponse> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getTitle(),
                        course.getStatus(),
                        new CourseInstructorResponse(
                                course.getInstructor().getId(),
                                course.getInstructor().getName()
                        )
                ))
                .toList();
    }
}
