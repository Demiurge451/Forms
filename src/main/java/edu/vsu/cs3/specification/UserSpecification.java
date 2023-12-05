package edu.vsu.cs3.specification;

import edu.vsu.cs3.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasLogin(String login) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("login"), "%" + login + "%");
    }
}
