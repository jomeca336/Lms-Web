package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.InstructorDTO;
import edu.unimagdalena.lms.entities.Instructor;
import java.util.List;

public interface InstructorService {
    InstructorDTO.InstructorResponse create(InstructorDTO.InstructorCreateRequest request);
    InstructorDTO.InstructorResponse get(Long id);
    Instructor getObjectById(Long id);
    List<InstructorDTO.InstructorResponse> list();
    void delete(Long id);
    InstructorDTO.InstructorResponse update(Long id, InstructorDTO.InstructorUpdateRequest request);
}