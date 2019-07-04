package br.com.constock.userservice.port.adapter.jpa;

import br.com.constock.userservice.domain.user.Genre;
import br.com.constock.userservice.domain.user.User;

import java.time.LocalDate;
import java.util.UUID;

class UserRepositoryJpaTestFixture {

    private UserRepositoryJpaTestFixture() {
    }

    static User user() {
        return new User(UUID.fromString("d403f15c-516d-4b21-b6dd-1cc59c673c6c"),
                "James",
                "Gosling",
                null,
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                "mnb856vcx");
    }
}
