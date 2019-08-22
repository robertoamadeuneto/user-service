package br.com.maxplorer.userservice.adapter.jpa.event;

import br.com.maxplorer.userservice.core.domain.event.EventStore;

import java.time.OffsetDateTime;
import java.util.UUID;

class EventStoreRepositoryJpaTestFixture {

    private EventStoreRepositoryJpaTestFixture() {
    }

    static EventStore eventStore() {
        return new EventStore(UUID.fromString("d403f15c-516d-4b21-b6dd-1cc59c673c6c"),
                UUID.fromString("d403f15c-516d-4b21-b6dd-1cc59c673c6c"),
                "user.created",
                1,
                OffsetDateTime.now(),
                "{\\\"id\\\": \\\"d403f15c-516d-4b21-b6dd-1cc59c673c6c\\\", "
                        + "\\\"email\\\": \\\"james.gosling@email.com\\\", "
                        + "\\\"fullName\\\": \\\"James Gosling\\\"}");
    }
}
