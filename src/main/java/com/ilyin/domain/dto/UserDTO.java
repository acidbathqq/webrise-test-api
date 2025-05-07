package com.ilyin.domain.dto;


import com.ilyin.controller.UserController;
import com.ilyin.domain.Subscription;
import com.ilyin.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    private List<Subscription> subscriptions;

    public UserDTO(Long id, String name, List<Subscription> subscriptions) {

    }

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSubscriptions());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
