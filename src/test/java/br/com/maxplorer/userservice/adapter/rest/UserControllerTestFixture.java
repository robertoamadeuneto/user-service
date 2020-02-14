package br.com.maxplorer.userservice.adapter.rest;

import br.com.maxplorer.userservice.core.application.user.command.AuthenticateUserCommand;
import br.com.maxplorer.userservice.core.application.user.command.GenreCommand;
import br.com.maxplorer.userservice.core.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.core.application.user.query.GenreQuery;
import br.com.maxplorer.userservice.core.application.user.query.UserQuery;

import java.time.LocalDate;
import java.util.UUID;

class UserControllerTestFixture {

    private UserControllerTestFixture() {
    }

    static NewUserCommand newUserCommand() {
        return new NewUserCommand("James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                GenreCommand.MALE,
                "james.gosling@email.com",
                "mnb856vcx");
    }

    static UserQuery userQuery() {
        return new UserQuery(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James Gosling",
                LocalDate.of(1955, 5, 19),
                GenreQuery.MALE,
                "james.gosling@email.com");
    }

    static AuthenticateUserCommand authenticateUserCommand() {
        return new AuthenticateUserCommand("james.gosling@email.com",
                "mnb856vcx");
    }
}
