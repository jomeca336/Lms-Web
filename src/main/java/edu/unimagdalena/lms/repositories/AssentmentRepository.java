package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Assentment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssentmentRepository extends JpaRepository <Assentment, Long>{

    List<Assentment> findByCourseId(Long courseId);
}
