package edu.unimagdalena.lms.mappers;

import edu.unimagdalena.lms.dto.InstructorDTO;
import edu.unimagdalena.lms.entities.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {InstructorProfileMapper.class})
public interface InstructorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Instructor toEntity(InstructorDTO.InstructorCreateRequest request);

    InstructorDTO.InstructorResponse toDTO(Instructor instructor);

    InstructorDTO.InstructorBasicResponse toBasicDTO(Instructor instructor);

    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "courses", ignore = true)
    void updateEntity(@MappingTarget Instructor instructor, InstructorDTO.InstructorUpdateRequest request);
}