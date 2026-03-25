package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.InstructorProfileDTO;
import edu.unimagdalena.lms.entities.InstructorProfile;
import edu.unimagdalena.lms.mappers.InstructorProfileMapper;
import edu.unimagdalena.lms.repositories.InstructorProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorProfileServiceImpl implements InstructorProfileService {

    private final InstructorProfileRepository repository;
    private final InstructorProfileMapper mapper;

    @Override
    public InstructorProfileDTO.InstructorProfileResponse create(InstructorProfileDTO.InstructorProfileCreateRequest request) {
        InstructorProfile entity = mapper.toEntity(request);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorProfileDTO.InstructorProfileResponse get(Long id) {
        return mapper.toDTO(getObjectById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorProfile getObjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstructorProfileDTO.InstructorProfileResponse> list() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Profile not found");
        }
        repository.deleteById(id);
    }

    @Override
    public InstructorProfileDTO.InstructorProfileResponse update(Long id, InstructorProfileDTO.InstructorProfileUpdateRequest request) {
        InstructorProfile profile = getObjectById(id);
        mapper.updateEntity(profile, request);
        return mapper.toDTO(profile);
    }
}