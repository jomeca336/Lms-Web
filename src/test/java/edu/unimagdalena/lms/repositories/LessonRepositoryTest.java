package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class LessonRepositoryTest extends BaseTest {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldSaveLessonWithOrderIndex() {
        Course course = Course.builder().title("Termodinámica").build();
        courseRepository.save(course);

        Lesson lesson = Lesson.builder()
                .title("Introducción a la Entropía")
                .orderIndex(1)
                .course(course)
                .build();

        Lesson saved = lessonRepository.save(lesson);

        assertThat(saved.getId()).isGreaterThan(0);
        assertThat(saved.getOrderIndex()).isEqualTo(1);
        assertThat(saved.getCourse().getTitle()).isEqualTo("Termodinámica");
    }
}