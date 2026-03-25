package edu.unimagdalena.lms.mappers;

import edu.unimagdalena.lms.dto.InstructorProfileDTO;
import edu.unimagdalena.lms.entities.InstructorProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {InstructorMapper.class})
public interface InstructorProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instructor.id", source = "instructorId")
    InstructorProfile toEntity(InstructorProfileDTO.InstructorProfileCreateRequest request);

    InstructorProfileDTO.InstructorProfileResponse toDTO(InstructorProfile profile);

    @Mapping(target = "instructor.id", source = "instructorId")
    void updateEntity(@MappingTarget InstructorProfile profile, InstructorProfileDTO.InstructorProfileUpdateRequest request);
}