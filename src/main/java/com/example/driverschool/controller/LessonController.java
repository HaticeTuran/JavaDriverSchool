package com.example.driverschool.controller;

import com.example.driverschool.model.Instructor;
import com.example.driverschool.model.Lesson;
import com.example.driverschool.service.LessonService;
import com.example.driverschool.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
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
    public ResponseEntity<?> createLesson(@RequestBody Lesson lesson, Authentication authentication) {
        // Check if the authenticated user is an instructor
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_INSTRUCTOR"))) {
            // If the user is an instructor, create the lesson
            // Set the instructor of the lesson
            String username = authentication.getName();
            Instructor instructor = instructorService.getInstructorByUsername(username);
            lesson.setInstructor(instructor);

            Lesson createdLesson = lessonService.createLesson(lesson);
            return ResponseEntity.ok(createdLesson);
        } else {
            // If the user is not an instructor, return 403 Forbidden
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only instructors can create lessons");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody @Valid Lesson lesson) {
        Lesson updatedLesson = lessonService.updateLesson(id, lesson);
        if (updatedLesson != null) {
            return ResponseEntity.ok(updatedLesson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

    // You can add more endpoints or customize existing ones as needed
}
