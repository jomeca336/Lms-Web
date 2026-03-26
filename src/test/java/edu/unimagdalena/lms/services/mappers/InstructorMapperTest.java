package edu.unimagdalena.lms.services.mappers;


import edu.unimagdalena.lms.dto.InstructorDTO;
import edu.unimagdalena.lms.entities.Instructor;
import edu.unimagdalena.lms.mappers.InstructorMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;



public class InstructorMapperTest {
    private final InstructorMapper mapper = Mappers.getMapper(InstructorMapper.class);

    @Test
    void toEntity_ShouldMapFieldsCorrectly(){
        var req= new InstructorDTO.InstructorCreateRequest("test@email.com", "Juan Perez");
                Instructor entity = mapper.toEntity(req);

        assertThat(entity.getEmail()).isEqualTo("test@email.com");
        assertThat(entity.getFullName()).isEqualTo("Juan Perez");
    }
}

