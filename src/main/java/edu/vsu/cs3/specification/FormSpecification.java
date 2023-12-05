package edu.vsu.cs3.specification;

import edu.vsu.cs3.model.Form;
import org.springframework.data.jpa.domain.Specification;

public class FormSpecification {
    public static Specification<Form> hasTitle(String title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(title), "%" + title + "%"));
    }
}
