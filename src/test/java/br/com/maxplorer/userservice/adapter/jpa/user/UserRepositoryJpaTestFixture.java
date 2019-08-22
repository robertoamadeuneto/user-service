package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.Genre;
import br.com.maxplorer.userservice.core.domain.user.Password;
import br.com.maxplorer.userservice.core.domain.user.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

class UserRepositoryJpaTestFixture {

    private UserRepositoryJpaTestFixture() {
    }

    static User user() {
        return new User(UUID.fromString("d403f15c-516d-4b21-b6dd-1cc59c673c6c"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                new HashSet<>(Collections.singletonList(new Password("mnb856vcx"))));
    }
}
