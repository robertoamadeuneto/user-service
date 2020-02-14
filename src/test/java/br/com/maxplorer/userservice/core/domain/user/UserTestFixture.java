package br.com.maxplorer.userservice.core.domain.user;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

class UserTestFixture {

    private UserTestFixture() {
    }

    static User pendingUser() {
        return new User(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                Status.PENDING,
                Collections.singleton(new Password("$2a$10$HidlcwMBogXEH9rkAITAGuT4MDZHf/iWdKrbkOgZHL/fajwfMweWO", true)));
    }

    static User activeUser() {
        return new User(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                Status.ACTIVE,
                Collections.singleton(new Password("$2a$10$HidlcwMBogXEH9rkAITAGuT4MDZHf/iWdKrbkOgZHL/fajwfMweWO", true)));
    }

    static UserCreatedEvent userCreatedEvent() {
        return new UserCreatedEvent(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James Gosling",
                "james.gosling@email.com");
    }

    static UserActivatedEvent userActivatedEvent() {
        return new UserActivatedEvent(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James Gosling",
                "james.gosling@email.com");
    }
}
