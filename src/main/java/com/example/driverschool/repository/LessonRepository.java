package com.example.driverschool.repository;

import com.example.driverschool.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    // Find lessons by instructor
    List<Lesson> findByInstructorId(Long instructorId);

    // Find lessons by start time after a specified time
    List<Lesson> findByStartTimeAfter(LocalDateTime startTime);

    // Find lessons by end time before a specified time
    List<Lesson> findByEndTimeBefore(LocalDateTime endTime);

    // Add more custom query methods as needed
}
