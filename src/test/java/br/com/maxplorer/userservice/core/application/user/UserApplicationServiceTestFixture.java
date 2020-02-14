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
import java.util.UUID;

class UserApplicationServiceTestFixture {

    private UserApplicationServiceTestFixture() {
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

    static UserQuery userQuery() {
        return new UserQuery(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James Gosling",
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