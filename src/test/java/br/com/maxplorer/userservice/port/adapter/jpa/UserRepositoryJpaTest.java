package br.com.maxplorer.userservice.port.adapter.jpa;

import br.com.maxplorer.userservice.domain.user.Password;
import br.com.maxplorer.userservice.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Password.class)
public class UserRepositoryJpaTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserRepositorySpringData userRepositorySpringData;

    private UserRepositoryJpa userRepositoryJpa;

    @Before
    public void setUp() throws Exception {
        whenNew(BCryptPasswordEncoder.class).withAnyArguments().thenReturn(bCryptPasswordEncoder);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("$2y$12$V3ClcTwpJUbxOcw3gA.UG.NRC2brBJBkZKLiiCxdQFrsEEAlWKt2G");
        userRepositoryJpa = new UserRepositoryJpa(userRepositorySpringData);
    }

    @Test
    public void shouldFindByEmail() {

        final User user = UserRepositoryJpaTestFixture.user();
        when(userRepositorySpringData.findByEmail(any())).thenReturn(Optional.of(user));

        final Optional<User> foundUser = userRepositoryJpa.findByEmail("james.gosling@email.com");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get()).isEqualToComparingFieldByFieldRecursively(UserRepositoryJpaTestFixture.user());
    }

    @Test
    public void shouldSave() {

        final User user = UserRepositoryJpaTestFixture.user();

        userRepositoryJpa.save(user);

        verify(userRepositorySpringData).save(same(user));
    }
}
