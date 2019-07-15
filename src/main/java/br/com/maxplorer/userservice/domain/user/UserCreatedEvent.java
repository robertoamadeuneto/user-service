package br.com.maxplorer.userservice.domain.user;

import br.com.maxplorer.userservice.domain.event.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class UserCreatedEvent implements Event {

    private UUID id;
    private String fullName;
    private String email;

    @Override
    public String aggregateId() {
        return id.toString();
    }

    @Override
    public int eventVersion() {
        return 1;
    }

    @Override
    public String eventType() {
        return "user.created";
    }
}
