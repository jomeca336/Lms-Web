package edu.unimagdalena.lms.mappers;

import edu.unimagdalena.lms.dto.StudentDTO;
import edu.unimagdalena.lms.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {EnrollmentMapper.class, AssentmentMapper.class})
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "assentments", ignore = true)
    Student toEntity(StudentDTO.StudentCreateRequest request);

    StudentDTO.StudentResponse toDTO(Student student);

    StudentDTO.StudentBasicResponse toBasicDTO(Student student);

    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "assentments", ignore = true)
    void updateEntity(@MappingTarget Student student, StudentDTO.StudentUpdateRequest request);
}