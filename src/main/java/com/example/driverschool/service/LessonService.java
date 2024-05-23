package com.example.driverschool.service;

import com.example.driverschool.exception.ResourceNotFoundException;
import com.example.driverschool.model.Lesson;
import com.example.driverschool.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + id));
    }

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        Lesson existingLesson = getLessonById(id);
        existingLesson.setSubject(updatedLesson.getSubject());
        existingLesson.setInstructor(updatedLesson.getInstructor());
        existingLesson.setStartTime(updatedLesson.getStartTime());
        existingLesson.setEndTime(updatedLesson.getEndTime());
        // Set other properties as needed
        return lessonRepository.save(existingLesson);
    }

    public void deleteLesson(Long id) {
        Lesson existingLesson = getLessonById(id);
        lessonRepository.delete(existingLesson);
    }
}
