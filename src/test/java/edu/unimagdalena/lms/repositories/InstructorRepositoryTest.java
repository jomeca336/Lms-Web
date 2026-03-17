package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.entities.InstructorProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class InstructorRepositoryTest extends BaseTest {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorProfileRepository profileRepository;

    @Test
    void shouldSaveInstructorWithProfile() {
        Instructor instructor = Instructor.builder()
                .fullName("Prof. Alberto Ruiz")
                .email("alberto.ruiz@unimagdalena.edu.co")
                .createdAt(Instant.now())
                .build();
        Instructor savedInstructor = instructorRepository.save(instructor);


        InstructorProfile profile = InstructorProfile.builder()
                .bio("Experto en dinámica de sistemas.")
                .phone("3001234567")
                .instructor(savedInstructor)
                .build();
        InstructorProfile savedProfile = profileRepository.save(profile);


        assertThat(savedInstructor.getId()).isGreaterThan(0);
        assertThat(savedProfile.getInstructor().getFullName()).isEqualTo("Prof. Alberto Ruiz");
    }
}