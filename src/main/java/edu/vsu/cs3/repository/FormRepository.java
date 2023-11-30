package edu.vsu.cs3.repository;

import edu.vsu.cs3.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Integer> {
}
