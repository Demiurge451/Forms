package edu.vsu.cs3.repository;

import edu.vsu.cs3.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
