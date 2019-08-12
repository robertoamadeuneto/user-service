package br.com.maxplorer.userservice.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.UUID;

@JsonIgnoreProperties({"eventId", "aggregateId", "eventType", "eventVersion", "occurredOn"})
public interface Event {

    default UUID eventId() {
        return UUID.randomUUID();
    }

    UUID aggregateId();

    String eventType();

    int eventVersion();

    default OffsetDateTime occurredOn() {
        return OffsetDateTime.now();
    }
}