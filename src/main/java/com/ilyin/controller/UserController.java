package com.ilyin.controller;

import com.ilyin.domain.User;
import com.ilyin.domain.dto.UserDTO;
import com.ilyin.exception.ExceptionUtils;
import com.ilyin.exception.NotFoundException;
import com.ilyin.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
        var user = userService.getUserById(id);
        if (user.isEmpty()) {
            ExceptionUtils.logAndThrow(new NotFoundException(User.class, id));
        }

        return new UserDTO().fromEntity(user.get());
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
            ExceptionUtils.logAndThrow(new NotFoundException(User.class, id));
        }
        userService.deleteUser(id);
    }

}
