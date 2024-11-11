package com.clubconnect.clubconnect_backend.subscription;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clubconnect.clubconnect_backend.user.User;
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // 1. Subscribe to Event (Create a new subscription)
    @PostMapping("/event/{userId}/{eventId}")
    public ResponseEntity<Subscription> subscribeToEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        Subscription subscription = subscriptionService.subscribeToEvent(userId, eventId);
        return ResponseEntity.ok(subscription);
    }

    // 2. Get Subscription for a User and Event
    @GetMapping("/event/{userId}/{eventId}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long userId, @PathVariable Long eventId) {
        Subscription subscription = subscriptionService.getSubscription(userId, eventId);
        return ResponseEntity.ok(subscription);
    }

    // 3. Get All Subscriptions by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getSubscriptionsByUser(@PathVariable Long userId) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUser(userId);
        return ResponseEntity.ok(subscriptions);
    }

    // 4. Get All Subscribers for an Event
    @GetMapping("/event/{eventId}/subscribers")
    public ResponseEntity<List<User>> getSubscribersByEvent(@PathVariable Long eventId) {
        List<User> subscribers = subscriptionService.getSubscribersByEvent(eventId);
        return ResponseEntity.ok(subscribers);
    }

    // 5. Unsubscribe from Event
    @DeleteMapping("/event/{userId}/{eventId}")
    public ResponseEntity<Void> unsubscribeFromEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        subscriptionService.unsubscribeFromEvent(userId, eventId);
        return ResponseEntity.noContent().build();
    }
}
