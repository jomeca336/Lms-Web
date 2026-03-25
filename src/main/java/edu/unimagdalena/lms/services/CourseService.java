package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.CourseDTO;
import edu.unimagdalena.lms.entities.Course;
import java.util.List;

public interface CourseService {
    CourseDTO.CourseResponse create(CourseDTO.CourseCreateRequest request);
    CourseDTO.CourseResponse get(Long id);
    Course getObjectById(Long id);
    List<CourseDTO.CourseResponse> list();
    void delete(Long id);
    CourseDTO.CourseResponse update(Long id, CourseDTO.CourseUpdateRequest request);
}