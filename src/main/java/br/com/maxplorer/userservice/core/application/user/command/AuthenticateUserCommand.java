package br.com.maxplorer.userservice.core.application.user.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode
public class AuthenticateUserCommand {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
