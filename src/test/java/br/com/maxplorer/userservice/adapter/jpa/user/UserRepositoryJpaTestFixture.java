package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.Genre;
import br.com.maxplorer.userservice.core.domain.user.Password;
import br.com.maxplorer.userservice.core.domain.user.User;
import br.com.maxplorer.userservice.core.domain.user.Status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

class UserRepositoryJpaTestFixture {

    private UserRepositoryJpaTestFixture() {
    }

    static User user() {
        return new User(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                Status.PENDING,
                Collections.singleton(new Password("$2a$10$HidlcwMBogXEH9rkAITAGuT4MDZHf/iWdKrbkOgZHL/fajwfMweWO", true)));
    }
}
