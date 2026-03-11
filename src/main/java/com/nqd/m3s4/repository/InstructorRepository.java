package com.nqd.m3s4.repository;

import com.nqd.m3s4.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
