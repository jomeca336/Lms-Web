package edu.unimagdalena.lms.dto;

import java.io.Serializable;
import java.time.Instant;

public class EnrollmentDTO {

    public record EnrollmentCreateRequest(
            Long studentId,
            Long courseId,
            String status
    ) implements Serializable {}

    public record EnrollmentUpdateRequest(
            Long id,
            String status
    ) implements Serializable {}

    public record EnrollmentResponse(
            Long id,
            StudentDTO.StudentBasicResponse student,
            CourseDTO.CourseBasicResponse course,
            String status,
            Instant enrolledAt
    ) implements Serializable {}

    // Vista básica para usar en las listas de Estudiante o Curso
    public record EnrollmentStudentView(
            Long id,
            CourseDTO.CourseBasicResponse course,
            String status,
            Instant enrolledAt
    ) implements Serializable {}
}