package com.ilyin.repository;

import com.ilyin.domain.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    @Query(value = "SELECT s.service_name, COUNT(u.id) as user_count " +
            "FROM subscriptions s " +
            "JOIN users u ON s.user_fk = u.id " +
            "GROUP BY s.service_name " +
            "ORDER BY user_count DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<Object[]> getTopSubscriptions(int limit);
}
