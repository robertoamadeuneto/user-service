package br.com.maxplorer.userservice.core.domain.user;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
public class UserActivatedEvent extends UserEvent {

    public UserActivatedEvent(final UUID id,
                              final String fullName,
                              final String email) {
        super("user-service.user.activated", id, fullName, email);
    }
}
