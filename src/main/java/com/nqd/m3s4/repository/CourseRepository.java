package com.nqd.m3s4.repository;

import com.nqd.m3s4.model.Course;
import com.nqd.m3s4.model.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByIdAndStatus(Long id, CourseStatus status);
}
