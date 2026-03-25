package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.AssentmentDTO;
import edu.unimagdalena.lms.entities.Assentment;
import edu.unimagdalena.lms.mappers.AssentmentMapper;
import edu.unimagdalena.lms.repositories.AssentmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AssentmentServiceImpl implements AssentmentService {

    private final AssentmentRepository repository;
    private final AssentmentMapper mapper;

    @Override
    public AssentmentDTO.AssentmentResponse create(AssentmentDTO.AssentmentCreateRequest request) {
        Assentment entity = mapper.toEntity(request);
        entity.setTakenAt(Instant.now()); // Fecha de la evaluación
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public AssentmentDTO.AssentmentResponse get(Long id) {
        return mapper.toDTO(getObjectById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Assentment getObjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assentment not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssentmentDTO.AssentmentResponse> list() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Assentment not found");
        }
        repository.deleteById(id);
    }

    @Override
    public AssentmentDTO.AssentmentResponse update(Long id, AssentmentDTO.AssentmentUpdateRequest request) {
        Assentment assentment = getObjectById(id);
        mapper.updateEntity(assentment, request);
        return mapper.toDTO(assentment);
    }
}