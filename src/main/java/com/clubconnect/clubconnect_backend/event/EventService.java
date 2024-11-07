package com.clubconnect.clubconnect_backend.event;

import java.util.List;

public interface EventService {
    Event createEvent(EventDTO event);

    List<Event> getAllEvents();

    Event getEventById(Long id);

    Event updateEvent(Long id, EventDTO eventDetails);

    void deleteEvent(Long id);

    Event addUserToEvent(Long eventId, Long userId);
}
