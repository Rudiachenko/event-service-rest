package com.epam.impl.service;

import com.epam.dto.model.Event;
import com.epam.dto.model.EventRequest;
import com.epam.dto.model.EventResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EventMapper {

    public static Event eventRequestToEvent(EventRequest eventRequest) {
        log.debug("Mapping EventRequest to Event");
        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setPlace(eventRequest.getPlace());
        event.setSpeaker(eventRequest.getSpeaker());
        event.setEventType(eventRequest.getEventType());
        event.setDateTime(eventRequest.getDateTime());
        log.debug("Mapped Event: {}", event);
        return event;
    }

    public static EventResponse eventToEventResponse(Event event) {
        log.info("Mapping Event to EventResponse");
        EventResponse eventResponse = new EventResponse();
        eventResponse.setTitle(event.getTitle());
        eventResponse.setPlace(event.getPlace());
        eventResponse.setSpeaker(event.getSpeaker());
        eventResponse.setEventType(event.getEventType());
        eventResponse.setDateTime(event.getDateTime());
        log.debug("Mapped EventResponse: {}", eventResponse);
        return eventResponse;
    }
}