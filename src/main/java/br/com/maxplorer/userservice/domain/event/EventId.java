package br.com.constock.userservice.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class EventId implements Serializable {

    private UUID id;

    @JsonCreator
    EventId(String id) {
        this.id = UUID.fromString(id);
    }

    @JsonValue
    public UUID id() {
        return id;
    }
}