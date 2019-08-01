package br.com.maxplorer.userservice.domain.user;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

class UserTestFixture {

    private UserTestFixture() {
    }

    static User newUser() {
        return new User(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                new HashSet<>(Collections.singletonList(new Password("mnb856vcx"))));
    }
}
