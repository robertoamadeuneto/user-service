package br.com.maxplorer.userservice.domain.user;

import br.com.maxplorer.userservice.domain.event.EventRegistry;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Accessors(fluent = true)
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public static User newUser(UUID id,
                               String firstName,
                               String middleName,
                               String lastName,
                               LocalDate dateOfBirth,
                               Genre genre,
                               String email,
                               String password) {

        final User newUser = new User(id,
                firstName,
                middleName,
                lastName,
                dateOfBirth,
                genre,
                email,
                password);

        newUser.newUserEvent();

        return newUser;
    }

    private void newUserEvent() {
        EventRegistry.eventPublisher().publish(new UserCreatedEvent(id(), fullName(), email));
    }

    private String fullName() {
        return String.format("%s %s %s", firstName, middleName, lastName).trim();
    }
}
