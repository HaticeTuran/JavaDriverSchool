package com.example.driverschool.service;

import com.example.driverschool.model.Instructor;
import com.example.driverschool.model.User;
import com.example.driverschool.repository.InstructorRepository;
import com.example.driverschool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;


    @Autowired
    private UserRepository userRepository;

    public Instructor findInstructorById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (user instanceof Instructor) {
            return (Instructor) user;
        } else {
            throw new RuntimeException("User is not an instructor");
        }
    }
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Instructor instructor) {
        // Check if the instructor exists in the database
        if (instructor.getId() == null || !instructorRepository.existsById(instructor.getId())) {
            // Throw an exception or handle the scenario where the instructor does not exist
            return null;
        }
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    // Other methods to interact with the repository
}
