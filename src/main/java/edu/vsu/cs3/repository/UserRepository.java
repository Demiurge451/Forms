package edu.vsu.cs3.repository;

import edu.vsu.cs3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
