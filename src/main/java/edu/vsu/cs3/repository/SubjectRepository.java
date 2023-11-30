package edu.vsu.cs3.repository;

import edu.vsu.cs3.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
