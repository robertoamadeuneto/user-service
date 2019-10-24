package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.Genre;
import br.com.maxplorer.userservice.core.domain.user.Password;
import br.com.maxplorer.userservice.core.domain.user.User;
import br.com.maxplorer.userservice.core.domain.user.Status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

class UserRepositoryJpaSpringDataTestFixture {

    private UserRepositoryJpaSpringDataTestFixture() {
    }

    static String email() {
        return "james.gosling@email.com";
    }

    static User user() {
        User user = new User(UUID.fromString("4d5a7d69-0567-44eb-981e-49626fb5f434"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                email(),
                Status.PENDING,
                new HashSet<>(Collections.singletonList(new Password("mnb856vcx"))));

        user.passwords().forEach(p -> p.user(user));

        return user;
    }
}
