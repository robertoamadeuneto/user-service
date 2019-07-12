package br.com.constock.userservice.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.UUID;

@JsonIgnoreProperties({"eventId", "aggregateId", "eventType", "eventVersion", "occurredOn"})
public interface Event {

    default EventId eventId() {
        return new EventId(UUID.randomUUID().toString().toUpperCase());
    }

    String aggregateId();

    int eventVersion();

    String eventType();

    default OffsetDateTime occurredOn() {
        return OffsetDateTime.now();
    }
}