package com.clubconnect.clubconnect_backend.subscription;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
    List<Subscription> findByCategory(String category);
    List<Subscription> findByEventId(Long eventId);
    Optional<Subscription> findByUserIdAndEventId(Long userId, Long eventId);
}
