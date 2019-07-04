package br.com.constock.userservice.port.adapter.jpa;

import br.com.constock.userservice.domain.user.Genre;
import br.com.constock.userservice.domain.user.User;

import java.time.LocalDate;
import java.util.UUID;

class UserRepositorySpringDataTestFixture {

    private UserRepositorySpringDataTestFixture() {
    }

    static String email() {
        return "james.gosling@email.com";
    }

    static User user() {
        return new User(UUID.fromString("4d5a7d69-0567-44eb-981e-49626fb5f434"),
                "James",
                "Gosling",
                null,
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                "mnb856vcx");
    }
}
