package br.com.maxplorer.userservice.core.domain.user;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
public class UserCreatedEvent extends UserEvent {

    public UserCreatedEvent(UUID id, String fullName, String email) {
        super("user.created", id, fullName, email);
    }
}
