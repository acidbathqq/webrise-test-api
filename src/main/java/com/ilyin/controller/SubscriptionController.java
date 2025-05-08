package com.ilyin.controller;


import com.ilyin.domain.Subscription;
import com.ilyin.domain.dto.SubscriptionDTO;
import com.ilyin.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public SubscriptionDTO addSubscription(@PathVariable Long userId, @RequestBody SubscriptionDTO dto) {
        Subscription sub = new Subscription();
        sub.setServiceName(dto.getServiceName());

        return new SubscriptionDTO().fromEntity(
                subscriptionService.createSubscription(sub, userId)
        );
    }

}
