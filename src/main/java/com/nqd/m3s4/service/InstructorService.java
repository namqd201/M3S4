package com.nqd.m3s4.service;

import com.nqd.m3s4.dto.InstructorCreateRequest;
import com.nqd.m3s4.model.Instructor;
import com.nqd.m3s4.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor createInstructor(InstructorCreateRequest req) {

        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        return instructorRepository.save(instructor);
    }
}
