package edu.vsu.cs3.repository;

import edu.vsu.cs3.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
