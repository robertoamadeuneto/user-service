package br.com.constock.userservice.domain.user;

import br.com.constock.userservice.domain.event.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class UserCreatedEvent implements Event {

    private String fullName;
    private String email;
    private final String eventType = "user.created";

    @Override
    public String aggregateId() {
        return "";
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
