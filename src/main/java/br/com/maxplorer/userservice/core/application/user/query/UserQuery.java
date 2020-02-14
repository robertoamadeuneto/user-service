package br.com.maxplorer.userservice.core.application.user.query;

import br.com.maxplorer.userservice.core.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserQuery {

    private UUID id;
    private String fullName;
    private LocalDate dateOfBirth;
    private GenreQuery genre;
    private String email;

    public static UserQuery from(User user) {
        return new UserQuery(user.id(),
                user.fullName(),
                user.dateOfBirth(),
                GenreQuery.valueOf(user.genre().name()),
                user.email());
    }
}
