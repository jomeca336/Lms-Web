package edu.unimagdalena.lms.mappers;

import edu.unimagdalena.lms.dto.AssentmentDTO;
import edu.unimagdalena.lms.entities.Assentment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, CourseMapper.class})
public interface AssentmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "takenAt", ignore = true)
    @Mapping(target = "student.id", source = "studentId")
    @Mapping(target = "course.id", source = "courseId")
    Assentment toEntity(AssentmentDTO.AssentmentCreateRequest request);

    AssentmentDTO.AssentmentResponse toDTO(Assentment assentment);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateEntity(@MappingTarget Assentment assentment, AssentmentDTO.AssentmentUpdateRequest request);
}