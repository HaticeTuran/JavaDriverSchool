package com.example.driverschool.model;

import lombok.Getter;

import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Entity
@Table(name = "instructors")
public class Instructor extends User {

    // other fields

    // Getters and setters
    @OneToMany(mappedBy = "instructor")
    private List<Lesson> lessons;

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

}