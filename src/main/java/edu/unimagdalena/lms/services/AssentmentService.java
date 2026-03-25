package edu.unimagdalena.lms.services;

import edu.unimagdalena.lms.dto.AssentmentDTO;
import edu.unimagdalena.lms.entities.Assentment;
import java.util.List;

public interface AssentmentService {
    AssentmentDTO.AssentmentResponse create(AssentmentDTO.AssentmentCreateRequest request);
    AssentmentDTO.AssentmentResponse get(Long id);
    Assentment getObjectById(Long id);
    List<AssentmentDTO.AssentmentResponse> list();
    void delete(Long id);
    AssentmentDTO.AssentmentResponse update(Long id, AssentmentDTO.AssentmentUpdateRequest request);
}