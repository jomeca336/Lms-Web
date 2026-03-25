package edu.unimagdalena.lms.dto;

import java.io.Serializable;
import java.time.Instant;

public class AssentmentDTO {

    public record AssentmentCreateRequest(
            Long studentId,
            Long courseId,
            String type,
            int score
    ) implements Serializable {}

    public record AssentmentUpdateRequest(
            Long id,
            String type,
            int score
    ) implements Serializable {}

    public record AssentmentResponse(
            Long id,
            StudentDTO.StudentBasicResponse student,
            CourseDTO.CourseBasicResponse course,
            String type,
            int score,
            Instant takenAt
    ) implements Serializable {}


    public record AssentmentStudentView(
            Long id,
            CourseDTO.CourseBasicResponse course,
            String type,
            int score,
            Instant takenAt
    ) implements Serializable {}
}