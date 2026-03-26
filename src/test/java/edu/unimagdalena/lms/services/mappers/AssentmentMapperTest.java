package edu.unimagdalena.lms.services.mappers;

import edu.unimagdalena.lms.dto.AssentmentDTO;
import edu.unimagdalena.lms.entities.Assentment;
import edu.unimagdalena.lms.mappers.AssentmentMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;


public class AssentmentMapperTest{
    private final AssentmentMapper mapper = Mappers.getMapper(AssentmentMapper.class);

    @Test
    void toEntity_ShouldMapBothIds(){
        var req = new AssentmentDTO.AssentmentCreateRequest(1L, 2L, "QUIZ", 90);

        Assentment entity = mapper.toEntity(req);

        assertThat(entity.getStudent().getId()).isEqualTo(1L);
        assertThat(entity.getCourse().getId()).isEqualTo(2L);
        assertThat(entity.getType()).isEqualTo("QUIZ");
        assertThat(entity.getScore()).isEqualTo(90);

    }
}
