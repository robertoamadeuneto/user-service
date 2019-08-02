package br.com.maxplorer.userservice.application.user;

import br.com.maxplorer.userservice.application.user.command.GenreCommand;
import br.com.maxplorer.userservice.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.domain.user.Genre;
import br.com.maxplorer.userservice.domain.user.Password;
import br.com.maxplorer.userservice.domain.user.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
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

    static User user() {
        return new User(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                new HashSet<>(Collections.singletonList(new Password("mnb856vcx"))));
    }
}