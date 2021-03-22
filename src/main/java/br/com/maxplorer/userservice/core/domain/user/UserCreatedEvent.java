package br.com.maxplorer.userservice.core.domain.user;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
public class UserCreatedEvent extends UserEvent {

    public UserCreatedEvent(final UUID id,
                            final String fullName,
                            final String email) {
        super("user-service.user.created", id, fullName, email);
    }
}
