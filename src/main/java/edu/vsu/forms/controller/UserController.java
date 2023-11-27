package edu.vsu.forms.controller;

import edu.vsu.forms.dto.response.UserResponse;
import edu.vsu.forms.model.User;
import edu.vsu.forms.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getListOfUsers().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(userService.findById(id), UserResponse.class), HttpStatus.OK);
    }

    @PostMapping("/users")
    public HttpStatus createUser(@RequestBody User user) {
        userService.save(user);
        return HttpStatus.OK;
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable int id) {
        userService.delete(id);
        return HttpStatus.OK;
    }
}
