package br.com.maxplorer.userservice.adapter.messaging;

import br.com.maxplorer.userservice.core.domain.user.UserCreatedEvent;

import java.util.UUID;

class MessagingStreamTestFixture {

    private MessagingStreamTestFixture() {
    }

    static UserCreatedEvent userCreatedEvent() {
        return new UserCreatedEvent(UUID.randomUUID(), "James Gosling", "james.gosling@email.com");
    }
}
