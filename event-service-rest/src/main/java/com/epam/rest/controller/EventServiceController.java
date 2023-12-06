package com.epam.rest.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.epam.dto.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epam.api.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventServiceController {

    private final EventService eventService;

    @Autowired
    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    @GetMapping("/{id}")
    public EntityModel<Event> getEvent(@PathVariable Long id) {
        Event event = eventService.getEvent(id);
        return EntityModel.of(event,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventServiceController.class)
                                .getEvent(id)).
                        withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventServiceController.class)
                                .getAllEvents())
                        .withRel("events"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "View a list of available events", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public List<EntityModel<Event>> getAllEvents() {
        return eventService.getAllEvents().stream()
                .map(event -> EntityModel.of(event,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                        .methodOn(EventServiceController.class)
                                        .getEvent(event.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }

    @GetMapping("/title/{title}")
    public List<EntityModel<Event>> getAllEventsByTitle(@PathVariable String title) {
        return eventService.getAllEventsByTitle(title).stream()
                .map(event -> EntityModel.of(event,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                        .methodOn(EventServiceController.class)
                                        .getEvent(event.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}
