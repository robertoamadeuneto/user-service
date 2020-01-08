package br.com.maxplorer.userservice.core.domain.user;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
public class UserActivatedEvent extends UserEvent {

    public UserActivatedEvent(UUID id, String fullName, String email) {
        super("user.activated", id, fullName, email);
    }
}
