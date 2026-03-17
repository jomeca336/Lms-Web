package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class AssentmentRepositoryTest extends BaseTest {

    @Autowired private AssentmentRepository assentmentRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private InstructorRepository instructorRepository;
    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {

        Instructor instructor = instructorRepository.save(Instructor.builder()
                .fullName("Prof. Ingeniero")
                .email("profe@unimagdalena.edu.co")
                .createdAt(Instant.now())
                .build());


        course = courseRepository.save(Course.builder()
                .instructor(instructor)
                .title("Sistemas Dinámicos")
                .active(true)
                .build());


        student = studentRepository.save(Student.builder()
                .fullName("Estudiante Prueba")
                .email("test@mail.com")
                .build());
    }

    @Test
    void shouldSaveAssentment() {
        Assentment a = new Assentment();
         a.setStudent(student);
        a.setCourse(course);

        a.setScore(85);
        a.setType("TALLER");
        a.setTakenAt(Instant.now());

        Assentment guardado = assentmentRepository.save(a);

        assertThat(guardado.getId()).isGreaterThan(0);
        assertThat(guardado.getStudent().getFullName()).isEqualTo("Estudiante Prueba");
    }
}