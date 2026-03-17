package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.entities.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentRepositoryTest extends BaseTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldCreateEnrollmentForStudent() {

        Student student = Student.builder()
                .fullName("Gabriel García")
                .email("gabriel@student.com")
                .createdAt(Instant.now())
                .build();
        studentRepository.save(student);


        Course course = Course.builder()
                .title("Física II")
                .build();
        courseRepository.save(course);

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .status("ENROLLED")
                .enrolledAt(Instant.now())
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);

        assertThat(saved.getId()).isGreaterThan(0);
        assertThat(saved.getStudent().getFullName()).isEqualTo("Gabriel García");
    }
}