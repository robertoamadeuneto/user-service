package br.com.maxplorer.userservice.adapter.messaging;

import br.com.maxplorer.userservice.core.domain.user.UserCreatedEvent;

import java.util.UUID;

class MessagingStreamTestFixture {

    private MessagingStreamTestFixture() {
    }

    static UserCreatedEvent userCreatedEvent() {
        return new UserCreatedEvent(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"), "James Gosling", "james.gosling@email.com");
    }
}
