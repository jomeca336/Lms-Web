package edu.unimagdalena.lms.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public class StudentDTO {

    public record StudentCreateRequest(
            String email,
            String fullName
    ) implements Serializable {}

    public record StudentUpdateRequest(
            Long id,
            String email,
            String fullName
    ) implements Serializable {}

    public record StudentResponse(
            Long id,
            String email,
            String fullName,
            Instant createdAt,
            Instant updatedAt,
            Set<EnrollmentDTO.EnrollmentStudentView> enrollments,
            Set<AssentmentDTO.AssentmentStudentView> assentments
    ) implements Serializable {}

    public record StudentBasicResponse(
            Long id,
            String email,
            String fullName
    ) implements Serializable {}
}