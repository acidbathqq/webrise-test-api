package com.ilyin.service;

import com.ilyin.domain.Subscription;
import com.ilyin.domain.User;
import com.ilyin.exception.NotFoundException;
import com.ilyin.repository.SubscriptionRepository;
import com.ilyin.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public Subscription createSubscription(Subscription subscription, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(User.class, userId));

        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }

}
