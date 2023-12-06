package com.epam.impl.repository;

import com.epam.dto.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();

    List<Event> findByTitle(String title);
}
