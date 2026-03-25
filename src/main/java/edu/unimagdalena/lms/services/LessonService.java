package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.LessonDTO;
import edu.unimagdalena.lms.entities.Lesson;
import java.util.List;

public interface LessonService {
    LessonDTO.LessonResponse create(LessonDTO.LessonCreateRequest request);

    LessonDTO.LessonResponse get(Long id);

    Lesson getObjectById(Long id);

    List<LessonDTO.LessonResponse> list();

    void delete(Long id);

    LessonDTO.LessonResponse update(Long id, LessonDTO.LessonUpdateRequest request);
}