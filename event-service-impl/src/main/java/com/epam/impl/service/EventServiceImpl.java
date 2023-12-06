package com.epam.impl.service;

import com.epam.api.service.EventService;
import com.epam.impl.repository.EventRepository;
import lombok.extern.log4j.Log4j2;
import com.epam.dto.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Event createEvent(Event event) {
        log.info("Creating event: {}", event);
        Event createdEvent = eventRepository.save(event);
        log.info("Event created: {}", createdEvent);
        return createdEvent;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        log.info("Updating event with id: {}", id);
        Optional<Event> eventFromDb = eventRepository.findById(id);
        if (eventFromDb.isEmpty()) {
            log.error("It's impossible to update event with id: {} as it doesn't exist", id);
            throw new IllegalArgumentException("It's impossible to update event with id: " + id + " as it doesn't exist");
        }

        Event updatedEvent = eventRepository.save(event);
        log.info("Event updated: {}", updatedEvent);
        return updatedEvent;
    }

    @Override
    public Event getEvent(Long id) {
        log.info("Retrieving event with id: {}", id);
        Optional<Event> eventFromDb = eventRepository.findById(id);
        if (eventFromDb.isEmpty()) {
            log.error("It's impossible to get event with id: {} as it doesn't exist", id);
            throw new IllegalArgumentException("It's impossible to get event with id: " + id + " as it doesn't exist");
        }

        log.info("Event retrieved: {}", eventFromDb.get());
        return eventFromDb.get();
    }

    @Override
    public void deleteEvent(Long id) {
        log.info("Deleting event with id: {}", id);
        eventRepository.deleteById(id);
        log.info("Event with id: {} deleted", id);
    }

    @Override
    public List<Event> getAllEvents() {
        log.info("Retrieving all events");
        List<Event> events = eventRepository.findAll();
        log.info("Number of events retrieved: {}", events.size());
        return events;
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        log.info("Retrieving all events with title: {}", title);
        List<Event> events = eventRepository.findByTitle(title);
        log.info("Number of events with title '{}': {}", title, events.size());
        return events;
    }
}
