package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.StudentDTO;
import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.mappers.StudentMapper;
import edu.unimagdalena.lms.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    @Override
    public StudentDTO.StudentResponse create(StudentDTO.StudentCreateRequest request) {
        Student student = mapper.toEntity(request);
        student.setCreatedAt(Instant.now());
        student.setUpdatedAt(Instant.now());
        return mapper.toDTO(studentRepository.save(student));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO.StudentResponse get(Long id) {
        return mapper.toDTO(getObjectById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Student getObjectById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO.StudentResponse> list() {
        return studentRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO.StudentResponse update(Long id, StudentDTO.StudentUpdateRequest request) {
        Student student = getObjectById(id);
        mapper.updateEntity(student, request);
        student.setUpdatedAt(Instant.now());
        return mapper.toDTO(student);
    }
}