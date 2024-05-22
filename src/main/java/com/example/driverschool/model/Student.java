package com.example.driverschool.model;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
public class Student extends User {

    // Additional student-specific fields if needed
    // For example, studentId, grade, etc.

    // Constructors
    public Student(String username, String password, String email) {
        super(username, password, email);
    }

    public Student(String username, String password, String email, String role) {
        super(username, password, email, role);
    }

    public Student() {
        super();
    }
}