package com.clubconnect.clubconnect_backend.subscription;

import java.util.List;

import com.clubconnect.clubconnect_backend.user.User;

public interface SubscriptionService {
    Subscription subscribeToEvent(Long userId, Long eventId);
    Subscription getSubscription(Long userId, Long eventId);
    List<Subscription> getSubscriptionsByUser(Long userId);
    List<User> getSubscribersByEvent(Long eventId);
    void unsubscribeFromEvent(Long userId, Long eventId);
}
