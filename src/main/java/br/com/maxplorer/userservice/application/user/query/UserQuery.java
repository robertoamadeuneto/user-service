package br.com.maxplorer.userservice.application.user.query;

import br.com.maxplorer.userservice.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserQuery {

    private String fullName;
    private LocalDate dateOfBirth;
    private GenreQuery genre;
    private String email;

    public static UserQuery from(User user) {
        return new UserQuery(user.fullName(),
                user.dateOfBirth(),
                GenreQuery.valueOf(user.genre().name()),
                user.email());
    }
}
