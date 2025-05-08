package com.ilyin.service;

import com.ilyin.domain.Subscription;
import com.ilyin.domain.User;
import com.ilyin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.existsByName(user.getName())) {
            throw new IllegalArgumentException("User with this name already exists");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public User updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return getUserById(userId).map(User::getSubscriptions).orElse(new ArrayList<>());
    }

}