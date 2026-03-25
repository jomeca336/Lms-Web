package edu.unimagdalena.lms.mappers;

import edu.unimagdalena.lms.dto.LessonDTO;
import edu.unimagdalena.lms.entities.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course.id", source = "courseId")
    Lesson toEntity(LessonDTO.LessonCreateRequest request);

    LessonDTO.LessonResponse toDTO (Lesson lesson);

    @Mapping(target = "course.id", source = "courseId")
    void updateEntity(@MappingTarget Lesson lesson, LessonDTO.LessonUpdateRequest request);
}
