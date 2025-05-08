package com.ilyin.controller;


import com.ilyin.domain.Subscription;
import com.ilyin.domain.User;
import com.ilyin.domain.dto.Response;
import com.ilyin.domain.dto.SubscriptionDTO;
import com.ilyin.exception.NotFoundException;
import com.ilyin.service.SubscriptionService;
import com.ilyin.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;

    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @PostMapping
    public SubscriptionDTO addSubscription(@PathVariable Long userId, @RequestBody SubscriptionDTO dto) {
        Subscription sub = new Subscription();
        sub.setServiceName(dto.getServiceName());

        return new SubscriptionDTO().fromEntity(
                subscriptionService.createSubscription(sub, userId)
        );
    }

    @GetMapping()
    public List<SubscriptionDTO> getSubscriptionsByUserId(@PathVariable Long userId) {
        if (!userService.existsById(userId)) {
            throw new NotFoundException(User.class, userId);
        }

        List<Subscription> subscriptions = userService.getSubscriptionsByUserId(userId);
        return new SubscriptionDTO().fromEntityList(subscriptions);
    }

    @DeleteMapping("/{subId}")
    public void deleteSubscription(@PathVariable Long userId, @PathVariable Long subId) {
        if (!userService.existsById(userId)) {
            throw new NotFoundException(User.class, userId);
        }

        if (!subscriptionService.existsById(subId)) {
            throw new NotFoundException(Subscription.class, subId);
        }

        subscriptionService.deleteSubscription(subId);
    }

    @GetMapping("/top")
    public List<Response.TopSubscriptionResponseDTO> getTopSubscriptions(@RequestParam(defaultValue = "3") int limit) {
        return subscriptionService.getTopSubscriptions(limit);
    }

}
