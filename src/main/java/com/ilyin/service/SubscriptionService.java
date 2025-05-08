package com.ilyin.service;

import com.ilyin.domain.Subscription;
import com.ilyin.domain.User;
import com.ilyin.domain.dto.Response;
import com.ilyin.exception.ExceptionUtils;
import com.ilyin.exception.NotFoundException;
import com.ilyin.repository.SubscriptionRepository;
import com.ilyin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public Boolean existsById(Long id) {
        return subscriptionRepository.existsById(id);
    }

    public Subscription createSubscription(Subscription subscription, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(User.class, userId));

        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long subId, Long userId) {
        Subscription sub = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new NotFoundException(Subscription.class, subId));

        if (Optional.ofNullable(sub.getUser()).stream().anyMatch(u -> !u.getId().equals(userId))) {
            ExceptionUtils.logAndThrow(new IllegalArgumentException("Unable to delete subscription for another user"));
        }

        subscriptionRepository.deleteById(subId);
    }

    public List<Response.TopSubscriptionResponseDTO> getTopSubscriptions(int limit) {
        var topList = subscriptionRepository.getTopSubscriptions(limit);
        return topList.stream()
                .map(r -> {
                    String serviceName = (String) r[0];
                    Long userCount = (Long) r[1];
                    return new Response.TopSubscriptionResponseDTO(serviceName, userCount.intValue());
                }).toList();
    }

}
