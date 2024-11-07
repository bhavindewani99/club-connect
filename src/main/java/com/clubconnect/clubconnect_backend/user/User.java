package com.clubconnect.clubconnect_backend.user;

import java.util.HashSet;
import java.util.Set;

import com.clubconnect.clubconnect_backend.event.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Role is required")
    private String role; // "STUDENT" or "CLUB"

    // Constructors
    public User() {
    }

    public User(Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // 6th Nov code

    //@JsonBackReference
    @ManyToMany
    @JoinTable(
        name = "user_events",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonIgnoreProperties("attendees") // Prevents recursion by ignoring 'attendees' in Event
    private Set<Event> events = new HashSet<>();

    public Set<Event> getEvents() {
        return events;
    }

    public void setAttendees(Set<Event> events) {
        this.events = events;
    }


}