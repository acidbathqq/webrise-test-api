package com.ilyin.controller;

import com.ilyin.domain.Subscription;
import com.ilyin.domain.User;
import com.ilyin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//TODO exception handler
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public record UserDTO(Long id, String name, List<Subscription> subscriptions) {
        public static UserDTO from(User user) {
            return new UserDTO(user.getId(), user.getName(), user.getSubscriptions());
        }
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return Optional.ofNullable(userService.getUserById(id))
                .map(UserDTO::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name);
        user.setSubscriptions(userDTO.subscriptions);

        return UserDTO.from(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        user.setName(userDTO.name);

        return UserDTO.from(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
    }

}
