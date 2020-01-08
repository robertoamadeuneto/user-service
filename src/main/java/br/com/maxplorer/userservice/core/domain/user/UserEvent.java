package br.com.maxplorer.userservice.core.domain.user;

import br.com.maxplorer.userservice.core.domain.event.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public abstract class UserEvent implements Event {

    private String eventType;
    private UUID id;
    private String fullName;
    private String email;

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
