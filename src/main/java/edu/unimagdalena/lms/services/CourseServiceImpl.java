package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.CourseDTO;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.mappers.CourseMapper;
import edu.unimagdalena.lms.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Override
    public CourseDTO.CourseResponse create(CourseDTO.CourseCreateRequest request) {
        Course entity = mapper.toEntity(request);
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO.CourseResponse get(Long id) {
        return mapper.toDTO(getObjectById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Course getObjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO.CourseResponse> list() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Course not found");
        }
        repository.deleteById(id);
    }

    @Override
    public CourseDTO.CourseResponse update(Long id, CourseDTO.CourseUpdateRequest request) {
        Course course = getObjectById(id);
        mapper.updateEntity(course, request);
        course.setUpdatedAt(Instant.now());
        return mapper.toDTO(course);
    }
}