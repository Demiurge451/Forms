package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.UserRequest;
import edu.vsu.cs3.dto.response.UserResponse;
import edu.vsu.cs3.model.User;
import edu.vsu.cs3.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getListOfUsers().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(userService.findById(id), UserResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpStatus createUser(@RequestBody UserRequest userRequest) {
        userService.save(modelMapper.map(userRequest, User.class));
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable int id) {
        userService.delete(id);
        return HttpStatus.OK;
    }
}
