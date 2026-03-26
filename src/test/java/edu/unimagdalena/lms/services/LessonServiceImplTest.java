package edu.unimagdalena.lms.services;


import edu.unimagdalena.lms.entities.Lesson;
import edu.unimagdalena.lms.mappers.LessonMapper;
import edu.unimagdalena.lms.repositories.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LessonServiceImplTest {
    @Mock
    LessonRepository repo;
    @Spy
    LessonMapper mapper = Mappers.getMapper(LessonMapper.class);
    @InjectMocks
    LessonServiceImpl service;

    @Test
    void delete_ShouldCallRepository_WhenExists() {

        Long id = 1L;
        Lesson lesson = new Lesson();
        lesson.setId(id);

        when(repo.findById(id)).thenReturn(Optional.of(lesson));

        service.delete(id);

        verify(repo).deleteById(id);
    }

}
