package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryJpaTest {

    @Mock
    private UserRepositoryJpaSpringData userRepositoryJpaSpringData;

    private UserRepositoryJpa userRepositoryJpa;

    @Before
    public void setUp() {
        userRepositoryJpa = new UserRepositoryJpa(userRepositoryJpaSpringData);
    }

    @Test
    public void shouldFindByEmail() {

        final User user = UserRepositoryJpaTestFixture.user();
        when(userRepositoryJpaSpringData.findByEmail(any())).thenReturn(Optional.of(user));

        final Optional<User> foundUser = userRepositoryJpa.findByEmail("james.gosling@email.com");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get()).isEqualToComparingFieldByFieldRecursively(UserRepositoryJpaTestFixture.user());

        verify(userRepositoryJpaSpringData).findByEmail(same(user.email()));
    }

    @Test
    public void shouldSave() {

        final User user = UserRepositoryJpaTestFixture.user();

        userRepositoryJpa.save(user);

        verify(userRepositoryJpaSpringData).save(same(user));
    }
}
