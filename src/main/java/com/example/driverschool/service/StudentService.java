package com.example.driverschool.service;

import com.example.driverschool.model.Student;
import com.example.driverschool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        // Check if the student exists in the database
        if (student.getId() == null || !studentRepository.existsById(student.getId())) {
            // Handle the scenario where the student does not exist
            return null;
        }

        return studentRepository.save(student);
    }



    // Other methods for student management
}

