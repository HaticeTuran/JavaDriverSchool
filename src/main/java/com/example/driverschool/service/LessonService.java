package com.example.driverschool.service;

import com.example.driverschool.model.Lesson;
import com.example.driverschool.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        return lessonOptional.orElse(null);
    }

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Long id, Lesson lesson) {
        // Check if lesson with given id exists
        if (lessonRepository.existsById(id)) {
            lesson.setId(id); // Ensure the ID is set for update
            return lessonRepository.save(lesson);
        } else {
            return null; // Lesson not found
        }
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    // Add more methods for lesson management as needed
}
