DROP TABLE IF EXISTS events;

CREATE TABLE events
(
    id         bigserial    not null,
    title      varchar(255) not null,
    place      varchar(255) not null,
    speaker    varchar(255) not null,
    event_type varchar(255) not null,
    date_time  timestamp    not null,
    primary key (id)
);

INSERT INTO events(title, place, speaker, event_type, date_time)
VALUES ('Two days concert', 'Kyiv, Ukraine', 'LATEXFAUNA', 'concert', '2023-12-22 19:00'),
       ('NOTRE DAME DE PARIS Le Concert', 'Kyiv, Ukraine', 'Musical Band', 'concert', '2023-12-23 18:00'),
       ('Underground StandUp', 'Kyiv, Ukraine', 'Hanna Kochegura', 'comedy', '2023-12-15 19:00');
