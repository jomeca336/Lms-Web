package edu.unimagdalena.lms.mappers;

import edu.unimagdalena.lms.dto.CourseDTO;
import edu.unimagdalena.lms.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {InstructorMapper.class, LessonMapper.class, EnrollmentMapper.class})
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "assentments", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "instructor.id", source = "instructorId")
    Course toEntity(CourseDTO.CourseCreateRequest request);

    CourseDTO.CourseResponse toDTO(Course course);

    CourseDTO.CourseBasicResponse toBasicDTO(Course course);

    @Mapping(target = "instructor.id", source = "instructorId")
    @Mapping(target = "assentments", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    void updateEntity(@MappingTarget Course course, CourseDTO.CourseUpdateRequest request);
}