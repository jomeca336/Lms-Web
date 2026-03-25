package edu.unimagdalena.lms.services.mappers;

import edu.unimagdalena.lms.dto.EnrollmentDTO;
import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.mappers.EnrollmentMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentMapperTest {
    private final EnrollmentMapper mapper = Mappers.getMapper(EnrollmentMapper.class);

    @Test
    void toEntity_ShouldMapBothIds() {
        var req = new EnrollmentDTO.EnrollmentCreateRequest(10L, 20L, "ENROLLED");

        Enrollment entity = mapper.toEntity(req);

        assertThat(entity.getStudent().getId()).isEqualTo(10L);
        assertThat(entity.getCourse().getId()).isEqualTo(20L);
        assertThat(entity.getStatus()).isEqualTo("ENROLLED");
    }
}