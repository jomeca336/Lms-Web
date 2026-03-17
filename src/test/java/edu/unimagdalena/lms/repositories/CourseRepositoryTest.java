package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Instructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class CourseRepositoryTest extends BaseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    private Instructor savedInstructor;

    @BeforeEach
    void setUp() {

        Instructor instructor = Instructor.builder()
                .fullName("Dr. Juan Pérez")
                .email("juan.perez@unimagdalena.edu.co")
                .createdAt(Instant.now())
                .build();
        savedInstructor = instructorRepository.save(instructor);
    }

    @Test
    void shouldSaveCourse() {
        Course course = Course.builder()
                .title("Sistemas Dinámicos")
                .status("PUBLISHED")
                .active(true)
                .instructor(savedInstructor)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Course savedCourse = courseRepository.save(course);


        assertThat(savedCourse.getId()).isGreaterThan(0);
        assertThat(savedCourse.getTitle()).isEqualTo("Sistemas Dinámicos");
        assertThat(savedCourse.getInstructor().getFullName()).isEqualTo("Dr. Juan Pérez");
    }

    @Test
    void shouldFindCourseById() {
        Course course = courseRepository.save(Course.builder()
                .title("Mecánica de Fluidos")
                .active(true)
                .instructor(savedInstructor)
                .build());

        Optional<Course> foundCourse = courseRepository.findById(course.getId());

        assertThat(foundCourse).isPresent();
        assertThat(foundCourse.get().getTitle()).isEqualTo("Mecánica de Fluidos");
    }
}
