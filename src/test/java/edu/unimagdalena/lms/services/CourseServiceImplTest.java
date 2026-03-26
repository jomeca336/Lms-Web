package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.CourseDTO;
import edu.unimagdalena.lms.mappers.CourseMapper;
import edu.unimagdalena.lms.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @Mock
    CourseRepository repo;
    
    @Mock
    CourseMapper mapper;

    @InjectMocks 
    CourseServiceImpl service;

    @Test
    void create_ShouldSetTimestamps() {
        var req = new CourseDTO.CourseCreateRequest("Kotlin", "DRAFT", true, 1L);
        var course = new edu.unimagdalena.lms.entities.Course();
        course.setTitle("Kotlin");
        var response = new CourseDTO.CourseResponse(1L, "Kotlin", "DRAFT", true, null, null, null, null, null);
        
        when(mapper.toEntity(req)).thenReturn(course);
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));
        when(mapper.toDTO(any())).thenReturn(response);

        var res = service.create(req);

        assertThat(res).isNotNull();
        assertThat(res.title()).isEqualTo("Kotlin");
        verify(repo).save(argThat(c -> c.getCreatedAt() != null));
    }
}