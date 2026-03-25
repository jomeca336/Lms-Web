package edu.unimagdalena.lms.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public class InstructorDTO {

    public record InstructorCreateRequest(
            String email,
            String fullName
    ) implements Serializable {}

    public record InstructorUpdateRequest(
            Long id,
            String email,
            String fullName
    ) implements Serializable {}

    public record InstructorResponse(
            Long id,
            String email,
            String fullName,
            Instant createdAt,
            Instant updatedAt,
            InstructorProfileDTO.InstructorProfileResponse profile,
            Set<CourseDTO.CourseBasicResponse> courses
    ) implements Serializable {}

    // Vista básica para evitar bucles infinitos en CourseResponse
    public record InstructorBasicResponse(
            Long id,
            String email,
            String fullName
    ) implements Serializable {}
}