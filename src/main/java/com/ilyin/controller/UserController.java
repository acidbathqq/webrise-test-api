package com.ilyin.controller;

import com.ilyin.domain.User;
import com.ilyin.domain.dto.UserDTO;
import com.ilyin.exception.NotFoundException;
import com.ilyin.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//TODO exception handler
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(u -> new UserDTO().fromEntity(u))
                .orElseThrow(() -> new NotFoundException(User.class, id));
    }

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        User user = userDTO.toEntity();

        return new UserDTO().fromEntity(
                userService.createUser(user)
        );
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        user.setName(userDTO.getName());
        return new UserDTO().fromEntity(
                userService.updateUser(user)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            throw new NotFoundException(User.class, id);
        }
        userService.deleteUser(id);
    }

}
