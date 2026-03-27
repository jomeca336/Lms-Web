package edu.unimagdalena.lms.services;


import edu.unimagdalena.lms.dto.InstructorDTO;
import edu.unimagdalena.lms.dto.InstructorProfileDTO;
import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.entities.InstructorProfile;
import edu.unimagdalena.lms.mappers.InstructorProfileMapper;
import edu.unimagdalena.lms.repositories.InstructorProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructorProfileServiceImplTest {

    @Mock
    InstructorProfileRepository repo;

    @Spy
    InstructorProfileMapper mapper = Mappers.getMapper(InstructorProfileMapper.class);

    @InjectMocks
    InstructorProfileServiceImpl service;

    @Test
    void get_ShouldThrowException_WhenNotFound(){

        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.get(1L)).isInstanceOf(EntityNotFoundException.class).hasMessageContaining("Profile not found with id: 1");
    }

    @Test
    void create_ShouldReturnResponse_WhenRequestIsValid(){
        // Given
        InstructorProfileDTO.InstructorProfileCreateRequest request = new InstructorProfileDTO.InstructorProfileCreateRequest(
                "123456789",
                "Bio text",
                1L
        );

        Instructor instructor = new Instructor();
        instructor.setId(1L);
        instructor.setFullName("Instructor name");
        instructor.setEmail("instructor@email.com");

        InstructorProfile entity = InstructorProfile.builder()
                .id(1L)
                .phone(request.phone())
                .bio(request.bio())
                .instructor(instructor)
                .build();

        when(repo.save(any(InstructorProfile.class))).thenReturn(entity);

        // When
        InstructorProfileDTO.InstructorProfileResponse response = service.create(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.phone()).isEqualTo(request.phone());
        assertThat(response.bio()).isEqualTo(request.bio());
        assertThat(response.instructor()).isNotNull();
        assertThat(response.instructor().id()).isEqualTo(1L);
    }

}
