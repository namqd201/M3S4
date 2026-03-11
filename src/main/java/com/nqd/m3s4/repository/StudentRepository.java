package com.nqd.m3s4.repository;

import com.nqd.m3s4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("""
        SELECT s
        FROM StudentEnrollment se
        JOIN se.student s
        WHERE se.course.id = :courseId
        AND LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%'))
        """)
    List<Student> searchStudents(Long courseId, String search);
}
