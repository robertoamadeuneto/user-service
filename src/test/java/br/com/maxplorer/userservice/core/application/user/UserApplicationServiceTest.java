package br.com.maxplorer.userservice.core.application.user;

import br.com.maxplorer.userservice.core.application.user.query.UserQuery;
import br.com.maxplorer.userservice.core.domain.event.EventPublisher;
import br.com.maxplorer.userservice.core.domain.event.EventRegistry;
import br.com.maxplorer.userservice.core.domain.exception.UserEmailAlreadyExistsException;
import br.com.maxplorer.userservice.core.domain.exception.UserNotFoundException;
import br.com.maxplorer.userservice.core.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserApplicationServiceTest {

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
    public void shouldRegisterNewUser() {

        userApplicationService.registerNewUser(UserApplicationServiceTestFixture.newUserCommand());

        verify(userRepository).save(any());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToRegisterNewUserWithAnEmailAlreadyInUse() {

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(UserApplicationServiceTestFixture.user()));

        assertThatThrownBy(() -> userApplicationService.registerNewUser(UserApplicationServiceTestFixture.newUserCommand()))
                .isInstanceOf(UserEmailAlreadyExistsException.class);
    }

    @Test
    public void shouldFindUserById() {

        when(userRepository.findById(any())).thenReturn(Optional.of(UserApplicationServiceTestFixture.user()));

        final UserQuery userQuery = userApplicationService.findUserById(UserApplicationServiceTestFixture.id());

        assertThat(userQuery).isEqualToComparingFieldByFieldRecursively(UserApplicationServiceTestFixture.userQuery());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToFindUserWithNonexistentId() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userApplicationService.findUserById(UserApplicationServiceTestFixture.id()))
                .isInstanceOf(UserNotFoundException.class);
    }
}
