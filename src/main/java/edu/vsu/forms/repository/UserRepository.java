package edu.vsu.forms.repository;

import edu.vsu.forms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
