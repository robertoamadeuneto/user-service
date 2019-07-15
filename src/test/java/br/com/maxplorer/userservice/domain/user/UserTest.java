package br.com.maxplorer.userservice.domain.user;

import br.com.maxplorer.userservice.domain.event.EventPublisher;
import br.com.maxplorer.userservice.domain.event.EventRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private EventPublisher eventPublisher;

    @Before
    public void setUp() {
        EventRegistry.defineEventPublisher(eventPublisher);
    }

    @Test
    public void shouldReturnNewUser() {

        final User newUser = User.newUser(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                null,
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                "mnb856vcx");

        assertThat(newUser).isEqualToComparingFieldByFieldRecursively(UserTestFixture.newUser());
    }
}
