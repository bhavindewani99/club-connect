package com.clubconnect.clubconnect_backend.event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);

    List<Event> getAllEvents();

    Event getEventById(Long id);

    Event updateEvent(Long id, Event eventDetails);

    void deleteEvent(Long id);

    Event addAttendee(Long eventId, Long userId);
}
