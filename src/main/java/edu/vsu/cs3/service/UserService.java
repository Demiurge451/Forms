package edu.vsu.cs3.service;

import edu.vsu.cs3.model.User;
import edu.vsu.cs3.repository.UserRepository;
import org.apache.logging.log4j.util.BiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getListOfUsers() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.getReferenceById(id);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.delete(findById(id));
    }

    @Transactional
    public User patch(int id, Map<String, Object> updates) {
        User user = userRepository.getReferenceById(id);
        Map<String, BiConsumer<User, String>> fieldSetters = Map.of(
                "login", User::setLogin,
                "password", User::setPassword,
                "phone", User::setPhone,
                "email", User:: setEmail
        );

        updates.forEach((key, value) -> {
            fieldSetters.getOrDefault(key, (entity, val) -> {}).accept(user, String.valueOf(value));
        });
        return user;
    }

    @Transactional
    public User update(int id, User updatedUser) {
        User existingUser = userRepository.getReferenceById(id);
        existingUser.setLogin(updatedUser.getLogin());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);
    }
}
