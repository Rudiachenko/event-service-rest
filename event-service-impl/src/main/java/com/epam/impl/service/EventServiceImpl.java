package com.epam.impl.service;

import com.epam.api.service.EventService;
import com.epam.dto.model.Event;
import com.epam.dto.model.EventRequest;
import com.epam.dto.model.EventResponse;
import com.epam.impl.repository.EventRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {
        Event event = EventMapper.eventRequestToEvent(eventRequest);
        log.info("Creating event: {}", event);
        Event createdEvent = eventRepository.save(event);
        log.info("Event created: {}", createdEvent);
        return EventMapper.eventToEventResponse(event);
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
        log.info("Updating event with id: {}", id);
        Optional<Event> eventFromDb = eventRepository.findById(id);
        if (eventFromDb.isEmpty()) {
            log.error("It's impossible to update event with id: {} as it doesn't exist", id);
            throw new NoSuchElementException("It's impossible to update event with id: " + id + " as it doesn't exist");
        }

        Event event = EventMapper.eventRequestToEvent(eventRequest);
        event.setId(id);

        Event updatedEvent = eventRepository.save(event);
        log.info("Event updated: {}", updatedEvent);

        return EventMapper.eventToEventResponse(updatedEvent);
    }

    @Override
    public EventResponse getEvent(Long id) {
        log.info("Retrieving event with id: {}", id);
        Optional<Event> eventFromDb = eventRepository.findById(id);
        if (eventFromDb.isEmpty()) {
            log.error("It's impossible to get event with id: {}, as it doesn't exist", id);
            throw new NoSuchElementException("It's impossible to get event with id: " + id + ", as it doesn't exist");
        }

        log.info("Event retrieved: {}", eventFromDb.get().toString());

        return EventMapper.eventToEventResponse(eventFromDb.get());
    }

    @Override
    public void deleteEvent(Long id) {
        log.info("Deleting event with id: {}", id);
        eventRepository.deleteById(id);
        log.info("Event with id: {} deleted", id);
    }

    @Override
    public void deleteAllEvents() {
        log.info("Deleting all events");
        eventRepository.deleteAll();
        log.info("Events deleted");
    }

    @Override
    public List<EventResponse> getAllEvents() {
        Iterable<Event> allEvents = eventRepository.findAllEvents();

        List<EventResponse> eventList = new ArrayList<>();
        allEvents.forEach(event -> eventList.add(EventMapper.eventToEventResponse(event)));

        log.info("Number of events retrieved: {}", eventList.size());
        return eventList;
    }

    @Override
    public List<EventResponse> getAllEventsByTitle(String title) {
        log.info("Retrieving all events with title: {}", title);
        List<Event> events = eventRepository.findByTitle(title);
        log.info("Number of events with title '{}': {}", title, events.size());

        return events.stream()
                .map(EventMapper::eventToEventResponse)
                .toList();
    }
}
