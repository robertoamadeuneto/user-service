package br.com.constock.userservice.port.adapter.jpa;

import br.com.constock.userservice.domain.user.User;
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
    private UserRepositorySpringData userRepositorySpringData;

    private UserRepositoryJpa userRepositoryJpa;

    @Before
    public void setUp() {
        userRepositoryJpa = new UserRepositoryJpa(userRepositorySpringData);
    }

    @Test
    public void shouldFindByEmail() {

        when(userRepositorySpringData.findByEmail(any()))
                .thenReturn(Optional.of(UserRepositoryJpaTestFixture.user()));

        final Optional<User> user = userRepositoryJpa.findByEmail("james.gosling@email.com");

        assertThat(user).isPresent();
        assertThat(user.get()).isEqualToComparingFieldByFieldRecursively(UserRepositoryJpaTestFixture.user());
    }

    @Test
    public void shouldSave() {

        final User user = UserRepositoryJpaTestFixture.user();

        userRepositoryJpa.save(user);

        verify(userRepositorySpringData).save(same(user));
    }
}
