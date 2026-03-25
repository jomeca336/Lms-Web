package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.InstructorProfileDTO;
import edu.unimagdalena.lms.entities.InstructorProfile;
import java.util.List;

public interface InstructorProfileService {
    InstructorProfileDTO.InstructorProfileResponse create(InstructorProfileDTO.InstructorProfileCreateRequest request);
    InstructorProfileDTO.InstructorProfileResponse get(Long id);
    InstructorProfile getObjectById(Long id);
    List<InstructorProfileDTO.InstructorProfileResponse> list();
    void delete(Long id);
    InstructorProfileDTO.InstructorProfileResponse update(Long id, InstructorProfileDTO.InstructorProfileUpdateRequest request);
}