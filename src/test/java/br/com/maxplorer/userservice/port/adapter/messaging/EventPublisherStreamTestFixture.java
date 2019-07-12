package br.com.constock.userservice.port.adapter.messaging;

import br.com.constock.userservice.domain.user.UserCreatedEvent;

class EventPublisherStreamTestFixture {

    private EventPublisherStreamTestFixture() {
    }

    static UserCreatedEvent userCreatedEvent() {
        return new UserCreatedEvent("James Gosling", "james.gosling@email.com");
    }
}
