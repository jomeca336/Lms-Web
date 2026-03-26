package edu.unimagdalena.lms.services.mappers;

import edu.unimagdalena.lms.dto.InstructorProfileDTO;
import edu.unimagdalena.lms.entities.InstructorProfile;
import edu.unimagdalena.lms.mappers.InstructorProfileMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class InstructorProfileMapperTest {
    private final InstructorProfileMapper mapper = Mappers.getMapper(InstructorProfileMapper.class);

    @Test
    void toEntity_shouldMapInstructorId(){
        var req= new InstructorProfileDTO.InstructorProfileCreateRequest("3001234567", "Profesor de matemáticas", 5L);

                InstructorProfile entity = mapper.toEntity(req);

        assertThat(entity.getPhone()).isEqualTo("3001234567");
        assertThat(entity.getBio()).isEqualTo("Profesor de matemáticas");
        assertThat(entity.getInstructor().getId()).isEqualTo(5L);

    }
}
