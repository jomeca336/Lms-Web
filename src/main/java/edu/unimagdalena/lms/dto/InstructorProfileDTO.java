package edu.unimagdalena.lms.dto;

import java.io.Serializable;

public class InstructorProfileDTO {

    public record InstructorProfileCreateRequest(
            String phone,
            String bio,
            Long instructorId
    ) implements Serializable {}

    public record InstructorProfileUpdateRequest(
            Long id,
            String phone,
            String bio,
            Long instructorId
    ) implements Serializable {}

    public record InstructorProfileResponse(
            Long id,
            String phone,
            String bio,
            InstructorDTO.InstructorBasicResponse instructor
    ) implements Serializable {}
}