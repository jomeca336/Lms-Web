package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.mappers.EnrollmentMapper;
import edu.unimagdalena.lms.repositories.EnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class EnrollmentServiceImplTest {
    @Mock EnrollmentRepository repo;
    @Spy EnrollmentMapper mapper = Mappers.getMapper(EnrollmentMapper.class);
    @InjectMocks EnrollmentServiceImpl service;

    @Test
    void delete_ShouldCallRepository_WhenExists() {
        when(repo.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repo).deleteById(1L); // Verifica que se llamó al borrado
    }
}
