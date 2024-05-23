package driverschool;

import com.example.driverschool.model.Lesson;
import com.example.driverschool.repository.LessonRepository;
import com.example.driverschool.service.LessonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private LessonService lessonService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateLesson() {
        // Create a lesson
        Lesson lesson = new Lesson();
        lesson.setSubject("Math");

        // Mock the lessonRepository's save method
        when(lessonRepository.save(lesson)).thenReturn(lesson);

        // Call the service method
        Lesson result = lessonService.createLesson(lesson);

        // Verify that the result matches the created lesson
        assertEquals(lesson.getSubject(), result.getSubject());
    }

    @Test
    public void testGetAllLessons() {
        // Create a list of lessons
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson());
        lessons.add(new Lesson());

        // Mock the lessonRepository's findAll method
        when(lessonRepository.findAll()).thenReturn(lessons);

        // Call the service method
        List<Lesson> result = lessonService.getAllLessons();

        // Verify that the result matches the mocked list
        assertEquals(lessons.size(), result.size());
    }

    @Test
    public void testGetLessonById() {
        // Create a lesson
        Lesson lesson = new Lesson();
        lesson.setId(1L);

        // Mock the lessonRepository's findById method
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));

        // Call the service method
        Lesson result = lessonService.getLessonById(1L);

        // Verify that the result matches the mocked lesson
        assertEquals(lesson.getId(), result.getId());
    }

    @Test
    public void testDeleteLesson() {

        doNothing().when(lessonRepository).deleteById(1L);

        lessonService.deleteLesson(1L);

        verify(lessonRepository, times(1)).deleteById(1L);
    }


}

