package com.clubconnect.clubconnect_backend.event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubconnect.clubconnect_backend.exception.ResourceNotFoundException;
import com.clubconnect.clubconnect_backend.user.User;
import com.clubconnect.clubconnect_backend.user.UserRepository;
import com.clubconnect.clubconnect_backend.user.UserService;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Event createEvent(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);

        Set<User> attendees = eventDTO.getAttendeeIds().stream()
            .map(id -> userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id)))
            .collect(Collectors.toSet());

        event.setAttendees(attendees);
        Event savedEvent = eventRepository.save(event);

        // Update each User's events set with the new event
        for (User attendee : attendees) {
            attendee.getEvents().add(savedEvent);
            userRepository.save(attendee); // Save updated user to persist the relationship
        }

        return savedEvent;
    }



    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public Event updateEvent(Long id, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(id)
                                            .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));
        
        // Update fields from EventDTO
        existingEvent.setTitle(eventDTO.getTitle());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setDate(eventDTO.getDate());
        existingEvent.setLocation(eventDTO.getLocation());
        existingEvent.setCategory(eventDTO.getCategory());
        
        // Fetch and update attendees
        Set<User> attendees = eventDTO.getAttendeeIds().stream()
                                    .map(userId -> userRepository.findById(userId)
                                                                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)))
                                    .collect(Collectors.toSet());
        existingEvent.setAttendees(attendees);
        
        return eventRepository.save(existingEvent);
    }




    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        eventRepository.delete(event);
    }


    public Event addUserToEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                          .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
        User user = userRepository.findById(userId)
                         .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    
        event.getAttendees().add(user);
        user.getEvents().add(event); // Update the user's events as well for bi-directional consistency
    
        userRepository.save(user); // Save both sides of the relationship
        return eventRepository.save(event);
    }

    private Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setCategory(eventDTO.getCategory());

        if (eventDTO.getAttendeeIds() != null) {
            Set<User> attendees = eventDTO.getAttendeeIds().stream()
                    .map(userService::getUserById) // Assuming getUserById fetches user by ID
                    .collect(Collectors.toSet());
            event.setAttendees(attendees);
        } else {
            event.setAttendees(new HashSet<>());
        }

        return event;
    }

    @Override
    public List<Event> findEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }
    
}
