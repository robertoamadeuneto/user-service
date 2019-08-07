package br.com.maxplorer.userservice.port.adapter.rest;

import br.com.maxplorer.userservice.application.user.command.GenreCommand;
import br.com.maxplorer.userservice.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.application.user.query.GenreQuery;
import br.com.maxplorer.userservice.application.user.query.UserQuery;

import java.time.LocalDate;
import java.util.UUID;

class UserControllerTestFixture {

    private UserControllerTestFixture() {
    }

    static UUID id() {
        return UUID.randomUUID();
    }

    static String email() {
        return "james.gosling@email.com";
    }

    static NewUserCommand newUserCommand() {
        return new NewUserCommand("James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                GenreCommand.MALE,
                email(),
                "mnb856vcx");
    }

    static UserQuery userQuery() {
        return new UserQuery("James Gosling",
                LocalDate.of(1955, 5, 19),
                GenreQuery.MALE,
                "james.gosling@email.com");
    }
}
