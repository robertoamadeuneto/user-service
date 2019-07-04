package br.com.constock.userservice.application.user.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class NewUserCommand {

    @JsonProperty("firstName")
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @JsonProperty("middleName")
    @Size(min = 2, max = 50)
    private String middleName;

    @JsonProperty("lastName")
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @JsonProperty("dateOfBirth")
    @NotNull
    private LocalDate dateOfBirth;

    @JsonProperty("genre")
    @NotNull
    private GenreCommand genre;

    @JsonProperty("email")
    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @JsonProperty("password")
    @NotNull
    @Size(max = 50)
    private String password;
}
