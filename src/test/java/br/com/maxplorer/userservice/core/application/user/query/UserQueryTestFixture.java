package br.com.maxplorer.userservice.core.application.user.query;

import br.com.maxplorer.userservice.core.domain.user.Genre;
import br.com.maxplorer.userservice.core.domain.user.Password;
import br.com.maxplorer.userservice.core.domain.user.User;
import br.com.maxplorer.userservice.core.domain.user.Status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

class UserQueryTestFixture {

    private UserQueryTestFixture() {
    }

    static User user() {
        return new User(UUID.randomUUID(),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                Status.PENDING,
                Collections.singleton(new Password("$2y$12$V3ClcTwpJUbxOcw3gA.UG.NRC2brBJBkZKLiiCxdQFrsEEAlWKt2G", true)));
    }

    static UserQuery userQuery() {
        return new UserQuery("James Gosling",
                LocalDate.of(1955, 5, 19),
                GenreQuery.MALE,
                "james.gosling@email.com");
    }
}
