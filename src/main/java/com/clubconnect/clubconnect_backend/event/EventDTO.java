package com.clubconnect.clubconnect_backend.event;

import java.time.LocalDateTime;
import java.util.Set;

public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String category;
    private Set<Long> attendeeIds; // IDs of the users attending this event

    // Constructors
    public EventDTO() {
    }

    public EventDTO(Long id, String title, String description, LocalDateTime date, String location, String category, Set<Long> attendeeIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.category = category;
        this.attendeeIds = attendeeIds;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Long> getAttendeeIds() {
        return attendeeIds;
    }

    public void setAttendeeIds(Set<Long> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }
}
