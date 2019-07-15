package br.com.maxplorer.userservice.port.adapter.messaging;

import br.com.maxplorer.userservice.domain.user.UserCreatedEvent;

import java.util.UUID;

class EventPublisherStreamTestFixture {

    private EventPublisherStreamTestFixture() {
    }

    static UserCreatedEvent userCreatedEvent() {
        return new UserCreatedEvent(UUID.randomUUID(), "James Gosling", "james.gosling@email.com");
    }
}
