package br.com.maxplorer.userservice.application.user;

import br.com.maxplorer.userservice.application.user.command.GenreCommand;
import br.com.maxplorer.userservice.application.user.command.NewUserCommand;

import java.time.LocalDate;

class UserApplicationServiceTestFixture {

    private UserApplicationServiceTestFixture() {
    }

    static NewUserCommand newUserCommand() {
        return new NewUserCommand("James",
                null,
                "Gosling",
                LocalDate.of(1955, 5, 19),
                GenreCommand.MALE,
                "james.gosling@email.com",
                "mnb856vcx");
    }
}