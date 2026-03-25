package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.EnrollmentDTO;
import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.mappers.EnrollmentMapper;
import edu.unimagdalena.lms.repositories.EnrollmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository repository;
    private final EnrollmentMapper mapper;

    @Override
    public EnrollmentDTO.EnrollmentResponse create(EnrollmentDTO.EnrollmentCreateRequest request) {
        Enrollment entity = mapper.toEntity(request);
        entity.setEnrolledAt(Instant.now()); // Seteamos la fecha actual
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentDTO.EnrollmentResponse get(Long id) {
        return mapper.toDTO(getObjectById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Enrollment getObjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentDTO.EnrollmentResponse> list() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Enrollment not found");
        }
        repository.deleteById(id);
    }

    @Override
    public EnrollmentDTO.EnrollmentResponse update(Long id, EnrollmentDTO.EnrollmentUpdateRequest request) {
        Enrollment enrollment = getObjectById(id);
        mapper.updateEntity(enrollment, request);
        return mapper.toDTO(enrollment);
    }
}