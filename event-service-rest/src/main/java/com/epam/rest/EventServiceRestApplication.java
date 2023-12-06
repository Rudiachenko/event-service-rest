package com.epam.rest;

import com.epam.api.EventServiceApiApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EventServiceRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventServiceApiApplication.class, args);
    }
}
