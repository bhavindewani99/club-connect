package com.clubconnect.clubconnect_backend.subscription;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubconnect.clubconnect_backend.event.Event;
import com.clubconnect.clubconnect_backend.event.EventRepository;
import com.clubconnect.clubconnect_backend.exception.ResourceNotFoundException;
import com.clubconnect.clubconnect_backend.user.User;
import com.clubconnect.clubconnect_backend.user.UserRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Subscription subscribeToEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
    
        // Set the bidirectional relationship
        user.getEvents().add(event);
        event.getAttendees().add(user);
    
        // Save both sides of the relationship
        userRepository.save(user);
        eventRepository.save(event);
    
        // Create and save the subscription entity
        Subscription subscription = new Subscription(user, event);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscription(Long userId, Long eventId) {
        return subscriptionRepository.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found for userId " + userId + " and eventId " + eventId)
                );
    }

    @Override
    public List<Subscription> getSubscriptionsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public List<User> getSubscribersByEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("Event", "id", eventId);
        }
        return subscriptionRepository.findByEventId(eventId).stream()
                .map(Subscription::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public void unsubscribeFromEvent(Long userId, Long eventId) {
        Subscription subscription = subscriptionRepository.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found for userId " + userId + " and eventId " + eventId));
        subscriptionRepository.delete(subscription);
    }
}
