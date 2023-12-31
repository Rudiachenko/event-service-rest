package com.epam.dto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title should not be blank")
    @Size(max = 20)
    private String title;

    @NotBlank(message = "Place should not be blank")
    @Size(max = 20)
    private String place;

    @NotBlank(message = "Speaker should not be blank")
    @Size(max = 30)
    private String speaker;

    @NotBlank(message = "Event type should not be blank")
    @Column(name = "event_type")
    private String eventType;

    @NotBlank(message = "Date and time should not be blank")
    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
