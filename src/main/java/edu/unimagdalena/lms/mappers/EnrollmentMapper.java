package edu.unimagdalena.lms.mappers;

import edu.unimagdalena.lms.dto.EnrollmentDTO;
import edu.unimagdalena.lms.entities.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, CourseMapper.class})
public interface EnrollmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrolledAt", ignore = true)
    @Mapping(target = "student.id", source = "studentId")
    @Mapping(target = "course.id", source = "courseId")
    Enrollment toEntity(EnrollmentDTO.EnrollmentCreateRequest request);

    EnrollmentDTO.EnrollmentResponse toDTO(Enrollment enrollment);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateEntity(@MappingTarget Enrollment enrollment, EnrollmentDTO.EnrollmentUpdateRequest request);
}