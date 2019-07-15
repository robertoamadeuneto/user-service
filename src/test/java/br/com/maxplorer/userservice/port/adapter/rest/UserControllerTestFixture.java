package br.com.maxplorer.userservice.port.adapter.rest;

import br.com.maxplorer.userservice.application.user.command.GenreCommand;
import br.com.maxplorer.userservice.application.user.command.NewUserCommand;

import java.time.LocalDate;

class UserControllerTestFixture {

    private UserControllerTestFixture() {
    }

    static String email() {
        return "james.gosling@email.com";
    }

    static NewUserCommand newUserCommand() {
        return new NewUserCommand("James",
                null,
                "Gosling",
                LocalDate.of(1955, 5, 19),
                GenreCommand.MALE,
                email(),
                "mnb856vcx");
    }
}
