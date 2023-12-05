package edu.vsu.cs3.controller;

import edu.vsu.cs3.dto.request.UserRequest;
import edu.vsu.cs3.dto.response.UserResponse;
import edu.vsu.cs3.model.User;
import edu.vsu.cs3.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(required = false, defaultValue = "0") int page,
                                                       @RequestParam(required = false, defaultValue = "10") int size,
                                                       @RequestParam(required = false, defaultValue = "id") String sortParam) {
        return new ResponseEntity<>(userService.getListOfUsers(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortParam))).stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserResponse>> getUsersFilter(@RequestParam(required = false, defaultValue = "0") int page,
                                                       @RequestParam(required = false, defaultValue = "10") int size,
                                                       @RequestParam(required = false) String login) {
        return new ResponseEntity<>(userService.getListOfUsers(login, PageRequest.of(page, size)).stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper
                .map(userService.findById(id), UserResponse.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userService.save(modelMapper.map(userRequest, User.class));
        return new ResponseEntity<>(modelMapper.map(user, UserResponse.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int id, @RequestBody UserRequest userRequest) {
        User user = userService.update(id, modelMapper.map(userRequest, User.class));
        return new ResponseEntity<>(modelMapper.map(user, UserResponse.class), HttpStatus.OK);
    }
}
