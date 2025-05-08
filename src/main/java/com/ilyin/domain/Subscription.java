package com.ilyin.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_fk", "service_name"})
)
public class Subscription extends AbstractEntity {

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    public Subscription() {}

    public Subscription(Long id, String serviceName, User user) {
        this.id = id;
        this.serviceName = serviceName;
        this.user = user;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
