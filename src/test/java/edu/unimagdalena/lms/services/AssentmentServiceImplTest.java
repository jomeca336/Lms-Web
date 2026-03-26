package edu.unimagdalena.lms.services;


import edu.unimagdalena.lms.mappers.AssentmentMapper;
import edu.unimagdalena.lms.repositories.AssentmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssentmentServiceImplTest {

    @Mock
    AssentmentRepository repo;

    @Spy
    AssentmentMapper mapper = Mappers.getMapper(AssentmentMapper.class);

    @InjectMocks
    AssentmentServiceImpl service;

    @Test
    void get_ShouldThrowException_WhenNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.get(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Assentment not found");
    }

}
