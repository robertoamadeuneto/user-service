package br.com.maxplorer.userservice.core.domain.user;

import br.com.maxplorer.userservice.core.domain.event.EventRegistry;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
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

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre_id")
    private Genre genre;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_id")
    private Status status;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Password> passwords;

    public static User newUser(final UUID id,
                               final String firstName,
                               final String lastName,
                               final LocalDate dateOfBirth,
                               final Genre genre,
                               final String email,
                               final String password) {

        final User newUser = new User(id,
                firstName,
                lastName,
                dateOfBirth,
                genre,
                email,
                Status.PENDING,
                Collections.singleton(Password.newActivePassword(password)));

        newUser.publishUserCreatedEvent();

        return newUser;
    }

    private void publishUserCreatedEvent() {
        EventRegistry.eventPublisher().publish(new UserCreatedEvent(id(), fullName(), email));
    }

    public String fullName() {
        return String.format("%s %s", firstName, lastName).trim();
    }

    public void activate() {
        this.status = Status.ACTIVE;
        publishUserActivatedEvent();
    }

    private void publishUserActivatedEvent() {
        EventRegistry.eventPublisher().publish(new UserActivatedEvent(id(), fullName(), email));
    }

    public boolean isActive() {
        return status.equals(Status.ACTIVE);
    }

    public boolean isPasswordValid(final String password) {
        return passwords.stream().filter(Password::active).findFirst().get().matches(password);
    }
}
