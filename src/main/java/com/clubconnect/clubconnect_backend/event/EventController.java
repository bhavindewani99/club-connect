package com.clubconnect.clubconnect_backend.event;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clubconnect.clubconnect_backend.club.ClubService;
import com.clubconnect.clubconnect_backend.user.User;
import com.clubconnect.clubconnect_backend.user.UserService;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final ClubService clubService;

    @Autowired
    public EventController(EventService eventService, UserService userService, ClubService clubService) {
        this.eventService = eventService;
        this.userService = userService;
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        // Pass EventDTO directly to the service layer
        Event createdEvent = eventService.createEvent(eventDTO);

        // Convert the created Event entity to EventDTO for the response
        EventDTO responseDto = convertToDto(createdEvent);
        return ResponseEntity.ok(responseDto);
    }

            
    // Get all events
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        List<EventDTO> eventDTOs = events.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(eventDTOs);
    }

    // Get an event by ID
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        EventDTO eventDTO = convertToDto(event);
        return ResponseEntity.ok(eventDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Event updatedEvent = eventService.updateEvent(id, eventDTO);
        EventDTO responseDto = convertToDto(updatedEvent);
        return ResponseEntity.ok(responseDto);
    }

    // Delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

   // Search events by category
    @GetMapping("/search")
    public ResponseEntity<List<EventDTO>> searchEventsByCategory(@RequestParam("category") String category) {
        List<Event> events = eventService.findEventsByCategory(category);
        List<EventDTO> eventDTOs = events.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(eventDTOs);
    }


    private EventDTO convertToDto(Event event) {
        Set<Long> attendeeIds = event.getAttendees().stream().map(User::getId).collect(Collectors.toSet());
        Set<String> tags = event.getTags(); // Assuming Event has a getTags() method
    
        return new EventDTO(
            event.getId(),
            event.getTitle(),
            event.getDescription(),
            event.getDate(),
            event.getLocation(),
            event.getCategory(),
            attendeeIds,
            event.getClub() != null ? event.getClub().getId() : null,
            tags
        );
    }
    
    private Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setCategory(eventDTO.getCategory());
        event.setTags(eventDTO.getTags());
    
        if (eventDTO.getAttendeeIds() != null) {
            Set<User> attendees = eventDTO.getAttendeeIds().stream()
                    .map(userService::getUserById)
                    .collect(Collectors.toSet());
            event.setAttendees(attendees);
        }
    
        return event;
    }
}
