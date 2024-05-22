package com.example.driverschool.repository;

import com.example.driverschool.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    List<Instructor> findByRole(String role);

    Instructor  findByUsername(String username);
}
