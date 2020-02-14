package br.com.maxplorer.userservice.core.domain.user;

import br.com.maxplorer.userservice.core.domain.event.EventPublisher;
import br.com.maxplorer.userservice.core.domain.event.EventRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Password.class)
public class UserTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private EventPublisher eventPublisher;

    @Before
    public void setUp() {
        EventRegistry.defineEventPublisher(eventPublisher);
    }

    @Test
    public void shouldReturnNewUser() throws Exception {

        whenNew(BCryptPasswordEncoder.class).withAnyArguments().thenReturn(bCryptPasswordEncoder);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("$2a$10$HidlcwMBogXEH9rkAITAGuT4MDZHf/iWdKrbkOgZHL/fajwfMweWO");

        final User newUser = User.newUser(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                "mnb856vcx");

        assertThat(newUser).isEqualToComparingFieldByFieldRecursively(UserTestFixture.pendingUser());

        verify(eventPublisher).publish(eq(UserTestFixture.userCreatedEvent()));
    }

    @Test
    public void shouldReturnFullName() {

        final String fullName = User.newUser(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"),
                "James",
                "Gosling",
                LocalDate.of(1955, 5, 19),
                Genre.MALE,
                "james.gosling@email.com",
                "mnb856vcx")
                .fullName();

        assertThat(fullName).isEqualTo("James Gosling");
    }

    @Test
    public void shouldActivate() {

        final User user = UserTestFixture.pendingUser();

        user.activate();

        assertThat(user).isEqualToComparingFieldByFieldRecursively(UserTestFixture.activeUser());

        verify(eventPublisher).publish(eq(UserTestFixture.userActivatedEvent()));
    }

    @Test
    public void shouldVerifyThatUserIsActive() {

        final User user = UserTestFixture.activeUser();

        final boolean isActive = user.isActive();

        assertThat(isActive).isTrue();
    }

    @Test
    public void shouldVerifyThatUserIsNotActive() {

        final User user = UserTestFixture.pendingUser();

        final boolean isActive = user.isActive();

        assertThat(isActive).isFalse();
    }

    @Test
    public void shouldVerifyThatPasswordIsValid() {

        final User user = UserTestFixture.pendingUser();

        final boolean isPasswordValid = user.isPasswordValid("mnb856vcx");

        assertThat(isPasswordValid).isTrue();
    }

    @Test
    public void shouldVerifyThatPasswordIsInvalid() {

        final User user = UserTestFixture.pendingUser();

        final boolean isPasswordValid = user.isPasswordValid("jio120pol");

        assertThat(isPasswordValid).isFalse();
    }
}
