package com.nqd.m3s4.service;

import com.nqd.m3s4.dto.CourseEnrollmentRequest;
import com.nqd.m3s4.model.Course;
import com.nqd.m3s4.model.CourseStatus;
import com.nqd.m3s4.model.Student;
import com.nqd.m3s4.model.StudentEnrollment;
import com.nqd.m3s4.repository.CourseRepository;
import com.nqd.m3s4.repository.StudentEnrollmentRepository;
import com.nqd.m3s4.repository.StudentRepository;
import com.nqd.m3s4.response.CourseEnrollmentResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentEnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository enrollmentRepository;

    public StudentEnrollmentService(StudentRepository studentRepository,
                                    CourseRepository courseRepository,
                                    StudentEnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public CourseEnrollmentResponse enrollStudent(Long courseId, CourseEnrollmentRequest req) {

        if (!courseRepository.existsByIdAndStatus(courseId, CourseStatus.ACTIVE)) {
            throw new RuntimeException("Course not active");
        }

        if (enrollmentRepository.existsByCourseIdAndStudentId(courseId, req.getStudentId())) {
            throw new RuntimeException("Student already enrolled");
        }

        Student student = studentRepository.findById(req.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        enrollmentRepository.save(enrollment);

        return new CourseEnrollmentResponse(
                student.getId(),
                course.getId(),
                enrollment.getEnrolledAt()
        );
    }

    public void dropStudent(Long courseId, Long studentId) {

        StudentEnrollment enrollment =
                enrollmentRepository.findByCourseIdAndStudentId(courseId, studentId)
                        .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollmentRepository.delete(enrollment);
    }
}
