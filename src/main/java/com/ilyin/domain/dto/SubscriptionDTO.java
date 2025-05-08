package com.ilyin.domain.dto;

import com.ilyin.domain.Subscription;
import jakarta.validation.constraints.NotNull;


public class SubscriptionDTO implements DtoMapper<Subscription, SubscriptionDTO> {

    private Long id;

    @NotNull(message = "Service name cannot be null")
    private String serviceName;

    public SubscriptionDTO() {}

    public SubscriptionDTO(Long id, String serviceName) {
        this.id = id;
        this.serviceName = serviceName;
    }

    @Override
    public Subscription toEntity() {
        Subscription subscription = new Subscription();
        subscription.setId(this.id);
        subscription.setServiceName(this.serviceName);
        return subscription;
    }

    @Override
    public SubscriptionDTO fromEntity(Subscription entity) {
        return new SubscriptionDTO(entity.getId(), entity.getServiceName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
