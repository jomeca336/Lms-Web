package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.StudentDTO;
import edu.unimagdalena.lms.entities.Student;
import java.util.List;

public interface StudentService {
    StudentDTO.StudentResponse create(StudentDTO.StudentCreateRequest request);
    StudentDTO.StudentResponse get(Long id);
    Student getObjectById(Long id);
    List<StudentDTO.StudentResponse> list();
    void delete(Long id);
    StudentDTO.StudentResponse update(Long id, StudentDTO.StudentUpdateRequest request);
}