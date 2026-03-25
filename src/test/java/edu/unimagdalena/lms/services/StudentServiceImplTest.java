package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.mappers.StudentMapper;
import edu.unimagdalena.lms.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock StudentRepository repo;
    @Spy StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
    @InjectMocks StudentServiceImpl service;

    @Test
    void get_ShouldThrowException_WhenNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.get(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Student not found");
    }
}