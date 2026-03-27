package edu.unimagdalena.lms.services.mappers;

import edu.unimagdalena.lms.dto.StudentDTO;
import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.mappers.StudentMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

class StudentMapperTest {
    private final StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Test
    void toEntity_ShouldMapSimpleFields() {
        var req = new StudentDTO.StudentCreateRequest("pedro@mail.com", "Pedro Marmol");

        Student entity = mapper.toEntity(req);

        assertThat(entity.getEmail()).isEqualTo("pedro@mail.com");
        assertThat(entity.getFullName()).isEqualTo("Pedro Marmol");
        assertThat(entity.getId()).isZero();
    }
}