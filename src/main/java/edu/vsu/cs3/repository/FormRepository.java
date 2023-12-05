package edu.vsu.cs3.repository;

import edu.vsu.cs3.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FormRepository extends JpaRepository<Form, Integer>, JpaSpecificationExecutor<Form> {
}
