package com.example.driverschool.controller;

import com.example.driverschool.model.Instructor;
import com.example.driverschool.model.Lesson;
import com.example.driverschool.model.User;
import com.example.driverschool.service.InstructorService;
import com.example.driverschool.service.LessonService;
import com.example.driverschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserService userService;


    @Autowired
    private InstructorService instructorService;


    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson != null) {
            return ResponseEntity.ok(lesson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody @Valid Lesson lesson, Authentication authentication) {
        Long instructorId = ((User) authentication.getPrincipal()).getId();
        Instructor instructor = instructorService.findInstructorById(instructorId);
        lesson.setInstructor(instructor);
        Lesson createdLesson = lessonService.createLesson(lesson);
        return ResponseEntity.ok(createdLesson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody @Valid Lesson lesson, @AuthenticationPrincipal User currentUser) {
        if (!"INSTRUCTOR".equals(currentUser.getRole())) {
            return ResponseEntity.status(403).body("Only instructors can update lessons");
        }

        Lesson updatedLesson = lessonService.updateLesson(id, lesson);
        if (updatedLesson != null) {
            return ResponseEntity.ok(updatedLesson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        if (!"INSTRUCTOR".equals(currentUser.getRole())) {
            return ResponseEntity.status(403).body("Only instructors can delete lessons");
        }

        lessonService.deleteLesson(id);
        return ResponseEntity.ok().build();
    }
}
