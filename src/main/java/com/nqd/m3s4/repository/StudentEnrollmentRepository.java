package com.nqd.m3s4.repository;

import com.nqd.m3s4.model.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
    boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);

    Optional<StudentEnrollment> findByCourseIdAndStudentId(Long courseId, Long studentId);
}
