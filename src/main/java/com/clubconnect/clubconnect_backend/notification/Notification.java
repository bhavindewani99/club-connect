package com.clubconnect.clubconnect_backend.notification;

import java.time.LocalDateTime;

import com.clubconnect.clubconnect_backend.event.Event;
import com.clubconnect.clubconnect_backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime createdAt;
    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("notifications") // Prevent recursion for User
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("notifications") // Prevent recursion for Event
    private Event event;

    // Constructors, Getters, and Setters

    public Notification() {}

    public Notification(String message, LocalDateTime createdAt, User user, Event event, String tag) {
        this.message = message;
        this.createdAt = createdAt;
        this.user = user;
        this.event = event;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
