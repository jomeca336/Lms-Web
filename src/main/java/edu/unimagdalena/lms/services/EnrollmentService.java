package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.EnrollmentDTO;
import edu.unimagdalena.lms.entities.Enrollment;
import java.util.List;

public interface EnrollmentService {
    EnrollmentDTO.EnrollmentResponse create(EnrollmentDTO.EnrollmentCreateRequest request);
    EnrollmentDTO.EnrollmentResponse get(Long id);
    Enrollment getObjectById(Long id);
    List<EnrollmentDTO.EnrollmentResponse> list();
    void delete(Long id);
    EnrollmentDTO.EnrollmentResponse update(Long id, EnrollmentDTO.EnrollmentUpdateRequest request);
}