package com.example.driverschool.controller;

import com.example.driverschool.model.Lesson;
import com.example.driverschool.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    // Other CRUD endpoints for lessons
}

