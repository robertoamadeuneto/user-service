package br.com.maxplorer.userservice.domain.user;

import br.com.maxplorer.userservice.domain.event.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class UserCreatedEvent implements Event {

    private UUID id;
    private String fullName;
    private String email;
    private final String eventType = "user.created";

    @Override
    public UUID aggregateId() {
        return id;
    }

    @Override
    public int eventVersion() {
        return 1;
    }

    @Override
    public String eventType() {
        return eventType;
    }
}
