package com.epam.api.service;

import com.epam.dto.model.EventRequest;
import com.epam.dto.model.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventRequest event);

    EventResponse updateEvent(Long id, EventRequest event);

    EventResponse getEvent(Long id);

    void deleteEvent(Long id);

    void deleteAllEvents();

    List<EventResponse> getAllEvents();

    List<EventResponse> getAllEventsByTitle(String title);
}
