package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.Password;
import br.com.maxplorer.userservice.core.domain.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(Password.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryJpaSpringDataTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserRepositoryJpaSpringData userRepositoryJpaSpringData;

    @Before
    public void setUp() throws Exception {
        whenNew(BCryptPasswordEncoder.class).withAnyArguments().thenReturn(bCryptPasswordEncoder);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("$2y$12$V3ClcTwpJUbxOcw3gA.UG.NRC2brBJBkZKLiiCxdQFrsEEAlWKt2G");
        transactionTemplate.execute((TransactionCallback<User>) status -> {
            userRepositoryJpaSpringData.save(UserRepositoryJpaSpringDataTestFixture.user());
            return null;
        });
    }

    @After
    public void tearDown() {
        JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "password", "cast(user_id as uuid)=?", UserRepositoryJpaSpringDataTestFixture.user().id());
        JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "\"user\"", "email=?", UserRepositoryJpaSpringDataTestFixture.email());
    }

    @Test
    public void shouldFindByEmail() {

        final Optional<User> user = userRepositoryJpaSpringData.findByEmail(UserRepositoryJpaSpringDataTestFixture.email());

        assertThat(user).isPresent();
        assertThat(user.get()).isEqualToComparingFieldByFieldRecursively(UserRepositoryJpaSpringDataTestFixture.user());
    }
}
