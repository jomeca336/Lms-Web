package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.InstructorDTO;
import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.mappers.InstructorMapper;
import edu.unimagdalena.lms.mappers.InstructorProfileMapper;
import edu.unimagdalena.lms.repositories.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    InstructorRepository repo;

    @Mock
    InstructorProfileMapper profileMapper;

    @Spy
    InstructorMapper mapper = Mappers.getMapper(InstructorMapper.class);

    @InjectMocks
    InstructorServiceImpl service;

    @Test
    void create_ShouldReturnResponseDTO_WhenSuccessful() {
        ReflectionTestUtils.setField(mapper, "instructorProfileMapper", profileMapper);
        var request = new InstructorDTO.InstructorCreateRequest("test@email.com", "Juan Perez");

        var instructorGuardado = new Instructor();
        instructorGuardado.setId(1L);
        instructorGuardado.setFullName("Juan Perez");
        instructorGuardado.setEmail("test@email.com");

        when(repo.save(any(Instructor.class))).thenReturn(instructorGuardado);

        var result = service.create(request);

        assertThat(result).isNotNull();
        assertThat(result.email()).isEqualTo("test@email.com");

        verify(repo, times(1)).save(any(Instructor.class));
    }

    @Test
    void delete_ShouldCallRepository_WhenInstructorExists() {
        Long id = 1L;

        when(repo.existsById(id)).thenReturn(true);

        service.delete(id);

        verify(repo).deleteById(id);
    }
}