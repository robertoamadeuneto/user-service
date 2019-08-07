package br.com.maxplorer.userservice.application.user.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class NewUserCommand {

    private String firstName;
    private String middleName;
    private LocalDate dateOfBirth;
    private GenreCommand genre;
    private String email;
    private String password;
}
