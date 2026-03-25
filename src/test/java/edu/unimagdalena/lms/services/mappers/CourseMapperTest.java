package edu.unimagdalena.lms.services.mappers;

import edu.unimagdalena.lms.dto.CourseDTO;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.mappers.CourseMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

class CourseMapperTest {
    private final CourseMapper mapper = Mappers.getMapper(CourseMapper.class);

    @Test
    void toEntity_ShouldMapInstructorId() {
        var req = new CourseDTO.CourseCreateRequest("Java Spring", "ACTIVE", true, 99L);

        Course entity = mapper.toEntity(req);

        assertThat(entity.getTitle()).isEqualTo("Java Spring");
        assertThat(entity.getInstructor()).isNotNull();
        assertThat(entity.getInstructor().getId()).isEqualTo(99L); // Prueba clave: el ID anidado
    }
}