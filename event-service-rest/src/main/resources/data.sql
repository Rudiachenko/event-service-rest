CREATE TABLE IF NOT EXISTS events (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255),
                                      place VARCHAR(255),
                                      speaker VARCHAR(255),
                                      event_type VARCHAR(255),
                                      date_time TIMESTAMP
);
INSERT INTO events (title, place, speaker, event_type, date_time) VALUES ('Tech Conference', 'New York', 'John Doe', 'Conference', '2023-12-05T09:00:00');
