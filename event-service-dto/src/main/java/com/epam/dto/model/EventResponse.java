package com.epam.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventResponse {

    @Schema(description = "Title of the event", example = "Two days concert")
    @NotBlank(message = "Title should not be blank")
    private String title;

    @Schema(description = "Place of the event", example = "Kyiv, Ukraine")
    @NotBlank(message = "Place should not be blank")
    private String place;

    @Schema(description = "Speaker of the event", example = "LATEXFAUNA")
    @NotBlank(message = "Speaker should not be blank")
    private String speaker;

    @Schema(description = "Type of the event", example = "concert")
    @NotBlank(message = "Event type should not be blank")
    private String eventType;

    @Schema(description = "Date and time of the event", example = "2023-12-22T19:00")
    private LocalDateTime dateTime;
}
