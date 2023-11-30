package edu.vsu.cs3.repository;

import edu.vsu.cs3.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
