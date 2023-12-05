package edu.vsu.cs3.specification;

import edu.vsu.cs3.model.Answer;
import org.springframework.data.jpa.domain.Specification;

public class AnswerSpecification {
    public static Specification<Answer> hasTxt(String txt) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("txt"), "%" + txt + "%");
    }
}
