package edu.vsu.cs3.specification;

import edu.vsu.cs3.model.Answer;
import edu.vsu.cs3.model.Question;
import org.springframework.data.jpa.domain.Specification;

public class QuestionSpecification {
    public static Specification<Question> hasTxt(String txt) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("txt"), "%" + txt + "%");
    }
}
