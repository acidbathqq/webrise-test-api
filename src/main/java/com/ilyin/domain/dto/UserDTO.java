package com.ilyin.domain.dto;


import com.ilyin.domain.Subscription;
import com.ilyin.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO implements DtoMapper<User, UserDTO> {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    private List<SubscriptionDTO> subscriptions;


    @Override
    public UserDTO fromEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.subscriptions = new SubscriptionDTO().fromEntityList(user.getSubscriptions());
        return this;
    }

    @Override
    public User toEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        if (subscriptions != null) {
            var subs = subscriptions.stream()
                    .map(subscriptionDTO -> {
                        var sub = subscriptionDTO.toEntity();
                        sub.setUser(user);
                        return sub;
                    }).collect(Collectors.toList());
            user.setSubscriptions(subs);
        }
        return user;
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

    public List<SubscriptionDTO> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionDTO> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
