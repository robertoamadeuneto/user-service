package br.com.maxplorer.userservice.core.application.user;

import br.com.maxplorer.userservice.core.application.user.command.AuthenticateUserCommand;
import br.com.maxplorer.userservice.core.application.user.query.UserQuery;
import br.com.maxplorer.userservice.core.domain.event.EventPublisher;
import br.com.maxplorer.userservice.core.domain.event.EventRegistry;
import br.com.maxplorer.userservice.core.domain.exception.UserEmailAlreadyExistsException;
import br.com.maxplorer.userservice.core.domain.exception.UserNotActiveException;
import br.com.maxplorer.userservice.core.domain.exception.UserNotFoundException;
import br.com.maxplorer.userservice.core.domain.exception.WrongEmailOrPasswordException;
import br.com.maxplorer.userservice.core.domain.user.Password;
import br.com.maxplorer.userservice.core.domain.user.User;
import br.com.maxplorer.userservice.core.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Password.class)
public class UserApplicationServiceTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private EventPublisher eventPublisher;

    @Mock
    private UserRepository userRepository;

    private UserApplicationService userApplicationService;

    @Before
    public void setUp() {
        EventRegistry.defineEventPublisher(eventPublisher);
        userApplicationService = new UserApplicationService(userRepository);
    }

    @Test
    public void shouldRegisterNewUser() throws Exception {

        whenNew(BCryptPasswordEncoder.class).withAnyArguments().thenReturn(bCryptPasswordEncoder);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("$2a$10$HidlcwMBogXEH9rkAITAGuT4MDZHf/iWdKrbkOgZHL/fajwfMweWO");
        when(userRepository.findByEmail(UserApplicationServiceTestFixture.newUserCommand().email())).thenReturn(Optional.empty());
        when(userRepository.newId()).thenReturn(UserApplicationServiceTestFixture.pendingUser().id());

        final UUID newUserId = userApplicationService.registerNewUser(UserApplicationServiceTestFixture.newUserCommand());

        assertThat(newUserId).isEqualTo(UserApplicationServiceTestFixture.pendingUser().id());

        verify(userRepository).findByEmail(UserApplicationServiceTestFixture.newUserCommand().email());
        verify(userRepository).newId();
        verify(userRepository).save(eq(UserApplicationServiceTestFixture.pendingUser()));
    }

    @Test
    public void shouldThrowExceptionWhenTryingToRegisterNewUserWithAnEmailAlreadyInUse() {

        final User pendingUser = UserApplicationServiceTestFixture.pendingUser();

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(pendingUser));

        assertThatThrownBy(() -> userApplicationService.registerNewUser(UserApplicationServiceTestFixture.newUserCommand()))
                .isInstanceOf(UserEmailAlreadyExistsException.class);

        verify(userRepository).findByEmail(eq(UserApplicationServiceTestFixture.pendingUser().email()));
    }

    @Test
    public void shouldFindUserById() {

        final User pendingUser = UserApplicationServiceTestFixture.pendingUser();

        when(userRepository.findById(any())).thenReturn(Optional.of(pendingUser));

        final UserQuery userQuery = userApplicationService.findUserById(UserApplicationServiceTestFixture.pendingUser().id());

        assertThat(userQuery).isEqualToComparingFieldByFieldRecursively(UserApplicationServiceTestFixture.userQuery());

        verify(userRepository).findById(eq(UserApplicationServiceTestFixture.pendingUser().id()));
    }

    @Test
    public void shouldThrowExceptionWhenTryingToFindUserWithNonExistentId() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userApplicationService.findUserById(UserApplicationServiceTestFixture.pendingUser().id()))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository).findById(eq(UserApplicationServiceTestFixture.pendingUser().id()));
    }

    @Test
    public void shouldActivateUser() {

        final User pendingUser = UserApplicationServiceTestFixture.pendingUser();

        when(userRepository.findById(any())).thenReturn(Optional.of(pendingUser));

        userApplicationService.activateUser(UserApplicationServiceTestFixture.pendingUser().id());

        verify(userRepository).findById(eq(UserApplicationServiceTestFixture.pendingUser().id()));
        verify(userRepository).save(eq(UserApplicationServiceTestFixture.activeUser()));
    }

    @Test
    public void shouldThrowExceptionWhenTryingToActivateUserWithNonExistentId() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userApplicationService.activateUser(UserApplicationServiceTestFixture.pendingUser().id()))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void shouldAuthenticateUser() {

        final User activeUser = UserApplicationServiceTestFixture.activeUser();

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(activeUser));

        final UserQuery query = userApplicationService.authenticateUser(UserApplicationServiceTestFixture.correctPasswordAuthenticateUserCommand());

        assertThat(query).isEqualToComparingFieldByFieldRecursively(UserApplicationServiceTestFixture.userQuery());

        verify(userRepository).findByEmail(eq(UserApplicationServiceTestFixture.activeUser().email()));
    }

    @Test
    public void shouldThrowExceptionWhenTryingToAuthenticateUserWithWrongEmail() {

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userApplicationService.authenticateUser(UserApplicationServiceTestFixture.correctPasswordAuthenticateUserCommand()))
                .isInstanceOf(WrongEmailOrPasswordException.class);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToAuthenticateUserWithWrongPassword() {

        final User activeUser = UserApplicationServiceTestFixture.activeUser();

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(activeUser));

        final AuthenticateUserCommand command = UserApplicationServiceTestFixture.wrongPasswordAuthenticateUserCommand();

        assertThatThrownBy(() -> userApplicationService.authenticateUser(command))
                .isInstanceOf(WrongEmailOrPasswordException.class);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToAuthenticateUserThatIsNotActive() {

        final User pendingUser = UserApplicationServiceTestFixture.pendingUser();

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(pendingUser));

        assertThatThrownBy(() -> userApplicationService.authenticateUser(UserApplicationServiceTestFixture.correctPasswordAuthenticateUserCommand()))
                .isInstanceOf(UserNotActiveException.class);
    }
}
