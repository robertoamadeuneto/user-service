package br.com.constock.userservice.application.user;

import br.com.constock.userservice.domain.event.EventPublisher;
import br.com.constock.userservice.domain.event.EventRegistry;
import br.com.constock.userservice.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.stub;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserApplicationService.class)
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
        stub(method(UUID.class, "randomUUID")).toReturn(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"));
    }

    @Test
    public void shouldRegisterNewUser() {

        userApplicationService.registerNewUser(UserApplicationServiceTestFixture.newUserCommand());

        verify(userRepository).save(any());
    }
}
