package edu.unimagdalena.lms.services;


import edu.unimagdalena.lms.dto.LessonDTO;
import edu.unimagdalena.lms.entities.Lesson;
import edu.unimagdalena.lms.mappers.LessonMapper;
import edu.unimagdalena.lms.repositories.LessonRepository;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LessonServiceImplTest {
    @Mock
    LessonRepository repo;
    @Spy
    LessonMapper mapper = Mappers.getMapper(LessonMapper.class);
    @InjectMocks
    LessonServiceImpl service;

    @Test
    void create_ShouldReturnSavedLesson() {
        LessonDTO.LessonCreateRequest request = new LessonDTO.LessonCreateRequest("Title", 1, 1L);
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        lesson.setTitle("Title");
        lesson.setOrderIndex(1);

        when(repo.save(any(Lesson.class))).thenReturn(lesson);

        LessonDTO.LessonResponse result = service.create(request);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.title()).isEqualTo("Title");
        verify(repo).save(any(Lesson.class));
    }

    @Test
    void delete_ShouldCallRepository_WhenExists() {
        Long id = 1L;
        when(repo.existsById(id)).thenReturn(true);

        service.delete(id);

        verify(repo).deleteById(id);
    }

    @Test
    void delete_ShouldThrowException_WhenNotExists() {
        Long id = 1L;
        when(repo.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> service.delete(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Cannot delete. Lesson not found");
    }

}
