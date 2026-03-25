package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.InstructorDTO;
import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.mappers.InstructorMapper;
import edu.unimagdalena.lms.repositories.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository repository;
    private final InstructorMapper mapper;

    @Override
    public InstructorDTO.InstructorResponse create(InstructorDTO.InstructorCreateRequest request) {
        Instructor entity = mapper.toEntity(request);
        // Aquí podrías setear manualmente createdAt si no usas @PrePersist
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorDTO.InstructorResponse get(Long id) {
        return mapper.toDTO(getObjectById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Instructor getObjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstructorDTO.InstructorResponse> list() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Instructor not found");
        }
        repository.deleteById(id);
    }

    @Override
    public InstructorDTO.InstructorResponse update(Long id, InstructorDTO.InstructorUpdateRequest request) {
        Instructor instructor = getObjectById(id);
        mapper.updateEntity(instructor, request);
        return mapper.toDTO(instructor);
    }
}