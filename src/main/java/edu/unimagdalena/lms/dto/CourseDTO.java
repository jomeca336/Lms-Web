package edu.unimagdalena.lms.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public class CourseDTO {

    public record CourseCreateRequest(
            String title,
            String status,
            boolean active,
            Long instructorId
    ) implements Serializable {}

    public record CourseUpdateRequest(
            Long id,
            String title,
            String status,
            boolean active,
            Long instructorId
    ) implements Serializable {}

    public record CourseResponse(
            Long id,
            String title,
            String status,
            boolean active,
            Instant createdAt,
            Instant updatedAt,
            InstructorDTO.InstructorBasicResponse instructor,
            Set<LessonDTO.LessonBasicResponse> lessons,
            Set<EnrollmentDTO.EnrollmentStudentView> enrollments
    ) implements Serializable {}

    public record CourseBasicResponse(
            Long id,
            String title,
            String status,
            boolean active
    ) implements Serializable {}
}