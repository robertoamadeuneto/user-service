package br.com.constock.userservice.port.adapter.rest;

import br.com.constock.userservice.application.user.command.GenreCommand;
import br.com.constock.userservice.application.user.command.NewUserCommand;

import java.time.LocalDate;

class UserControllerTestFixture {

    private UserControllerTestFixture() {
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
