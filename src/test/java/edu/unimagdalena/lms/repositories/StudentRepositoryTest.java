package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudentRepositoryTest extends BaseTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldSaveAndRetrieveStudent() {
            Student student = Student.builder()
                .fullName("Mariana Pineda")
                .email("mariana.pineda@unimagdalena.edu.co")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Student savedStudent = studentRepository.save(student);


        assertThat(savedStudent.getId()).isGreaterThan(0);

        Optional<Student> foundStudent = studentRepository.findById(savedStudent.getId());

        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getFullName()).isEqualTo("Mariana Pineda");
        assertThat(foundStudent.get().getEmail()).contains("@unimagdalena.edu.co");
    }

    @Test
    void shouldUpdateStudentEmail() {

        Student student = Student.builder()
                .fullName("Carlos Vives")
                .email("carlos.vives@old.com")
                .build();
        studentRepository.save(student);


        student.setEmail("carlos.vives@new.com");
        student.setUpdatedAt(Instant.now());
        Student updatedStudent = studentRepository.save(student);

        assertThat(updatedStudent.getEmail()).isEqualTo("carlos.vives@new.com");
    }
}