package com.clubconnect.clubconnect_backend.notification;

import java.time.LocalDateTime;

public class NotificationDTO {

    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private Long userId;
    private Long eventId;
    private String tag;

    // Default Constructor
    public NotificationDTO() {}

    // Constructor with all fields
    public NotificationDTO(Long id, String message, LocalDateTime createdAt, Long userId, Long eventId, String tag) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.userId = userId;
        this.eventId = eventId;
        this.tag = tag;
    }

    // Getters and Setters
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
