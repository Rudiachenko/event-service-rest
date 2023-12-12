package com.epam.rest.controller;

import com.epam.api.service.EventService;
import com.epam.dto.model.EventRequest;
import com.epam.dto.model.EventResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventServiceController {

    private final EventService eventService;

    @Autowired
    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Create a new event", tags = {"Create Event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created event"),
            @ApiResponse(responseCode = "400", description = "Invalid event input")
    })
    @PostMapping("/create")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest event) {
        EventResponse createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @Operation(summary = "View a list of available events", tags = {"Get all Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("/get/all")
    public ResponseEntity<CollectionModel<EventResponse>> getAllEvents() {
        List<EventResponse> eventResponses = eventService.getAllEvents();

        CollectionModel<EventResponse> collectionModel = CollectionModel.of(eventResponses);
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(EventServiceController.class)
                        .getAllEvents())
                .withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Get details of a specific event", tags = {"Get Event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved event"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/get/{id}")
    public EntityModel<EventResponse> getEvent(@PathVariable Long id) {
        EventResponse event = eventService.getEvent(id);
        return EntityModel.of(event,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventServiceController.class)
                                .getEvent(id)).
                        withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventServiceController.class)
                                .getAllEvents())
                        .withRel("events"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventServiceController.class)
                                .deleteEvent(id))
                        .withRel("delete this event"));
    }

    @Operation(summary = "View a list of available events by title", tags = {"Get Events by Title"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "No events found with given title")
    })
    @GetMapping("/get/title/{title}")
    public ResponseEntity<CollectionModel<EventResponse>> getAllEventsByTitle(@PathVariable String title) {
        List<EventResponse> eventResponses = eventService.getAllEventsByTitle(title);

        CollectionModel<EventResponse> collectionModel = CollectionModel.of(eventResponses);
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                                .methodOn(EventServiceController.class)
                                .getAllEventsByTitle(title))
                        .withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventServiceController.class)
                                .getAllEvents())
                        .withRel("events"));

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Update an existing event", tags = {"Update Event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated event"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable Long id, @RequestBody EventRequest event) {
        EventResponse updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    @Operation(summary = "Delete a specific event", tags = {"Delete Event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted event"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete all event", tags = {"Delete Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted event")
    })
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllEvents() {
        eventService.deleteAllEvents();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
