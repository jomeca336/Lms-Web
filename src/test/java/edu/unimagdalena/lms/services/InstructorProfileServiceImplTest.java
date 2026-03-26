package edu.unimagdalena.lms.services;


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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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

        assertThatThrownBy(() -> service.get(1L)).isInstanceOf(EntityNotFoundException.class).hasMessageContaining("InstructorProfile not found");
    }

}
