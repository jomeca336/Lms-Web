package edu.unimagdalena.lms.services.mappers;

import edu.unimagdalena.lms.dto.LessonDTO;
import edu.unimagdalena.lms.entities.Lesson;
import edu.unimagdalena.lms.mappers.LessonMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;


public class LessonMapperTest {
    private final LessonMapper mapper = Mappers.getMapper(LessonMapper.class);
    @Test
    void toEntity_ShouldMapFieldsCorrectly() {
        var req= new LessonDTO.LessonCreateRequest("Intro a Spring", 1, 10L);

        Lesson entity = mapper.toEntity(req);
        assertThat(entity.getTitle()).isEqualTo("Intro a Spring");
        assertThat(entity.getOrderIndex()).isEqualTo(1);
        assertThat(entity.getCourse().getId()).isEqualTo(10L);
    }

}

