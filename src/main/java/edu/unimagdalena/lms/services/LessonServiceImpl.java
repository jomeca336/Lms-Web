package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.LessonDTO;
import edu.unimagdalena.lms.entities.Lesson;
import edu.unimagdalena.lms.mappers.LessonMapper;
import edu.unimagdalena.lms.repositories.LessonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper mapper;

    @Override
    public LessonDTO.LessonResponse create(LessonDTO.LessonCreateRequest request) {
        Lesson entity = mapper.toEntity(request);
        Lesson saved = lessonRepository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LessonDTO.LessonResponse get(Long id) {
        return mapper.toDTO(getObjectById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Lesson getObjectById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonDTO.LessonResponse> list() {
        return lessonRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Lesson not found");
        }
        lessonRepository.deleteById(id);
    }

    @Override
    public LessonDTO.LessonResponse update(Long id, LessonDTO.LessonUpdateRequest request) {
        Lesson lesson = getObjectById(id);
        mapper.updateEntity(lesson, request);
        return mapper.toDTO(lesson);
    }
}