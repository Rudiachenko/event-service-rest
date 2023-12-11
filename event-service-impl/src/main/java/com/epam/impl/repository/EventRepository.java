package com.epam.impl.repository;

import com.epam.dto.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("SELECT event FROM Event event")
    List<Event> findAllEvents();

    List<Event> findByTitle(String title);
}
