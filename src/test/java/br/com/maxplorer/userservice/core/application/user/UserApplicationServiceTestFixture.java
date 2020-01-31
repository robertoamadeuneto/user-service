package br.com.maxplorer.userservice.core.application.user;

import br.com.maxplorer.userservice.core.application.user.command.AuthenticateUserCommand;
import br.com.maxplorer.userservice.core.application.user.command.GenreCommand;
import br.com.maxplorer.userservice.core.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.core.application.user.query.GenreQuery;
import br.com.maxplorer.userservice.core.application.user.query.UserQuery;
import br.com.maxplorer.userservice.core.domain.user.Genre;
import br.com.maxplorer.userservice.core.domain.user.Password;
import br.com.maxplorer.userservice.core.domain.user.Status;
import br.com.maxplorer.userservice.core.domain.user.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

class UserApplicationServiceTestFixture {

    private UserApplicationServiceTestFixture() {
    }

    static UUID userId() {
        return UUID.fromString("106c0fec-daea-47e5-9eba-3d16251047e8");
    }

    static NewUserCommand newUserCommand() {
        return new NewUserCommand("James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                GenreCommand.MALE,
                "james.gosling@email.com",
                "mnb856vcx");
    }

    static User pendingUser() {
        return new User(userId(),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                Status.PENDING,
                Collections.singleton(new Password("$2y$12$V3ClcTwpJUbxOcw3gA.UG.NRC2brBJBkZKLiiCxdQFrsEEAlWKt2G", true)));
    }

    static User activeUser() {
        return new User(userId(),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                Status.ACTIVE,
                Collections.singleton(new Password("$2y$12$V3ClcTwpJUbxOcw3gA.UG.NRC2brBJBkZKLiiCxdQFrsEEAlWKt2G", true)));
    }

    static UserQuery userQuery() {
        return new UserQuery("James Gosling",
                LocalDate.of(1955, 5, 19),
                GenreQuery.MALE,
                "james.gosling@email.com");
    }

    static AuthenticateUserCommand correctPasswordAuthenticateUserCommand() {
        return new AuthenticateUserCommand("james.gosling@email.com",
                "mnb856vcx");
    }

    static AuthenticateUserCommand wrongPasswordAuthenticateUserCommand() {
        return new AuthenticateUserCommand("james.gosling@email.com",
                "jio120pol");
    }
}