package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryJpaSpringDataTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserRepositoryJpaSpringData userRepositoryJpaSpringData;

    @Before
    public void setUp() {
        transactionTemplate.execute(status -> userRepositoryJpaSpringData.save(UserRepositoryJpaSpringDataTestFixture.user()));
    }

    @After
    public void tearDown() {
        JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "password", "cast(user_id as uuid)=?", UserRepositoryJpaSpringDataTestFixture.user().id());
        JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "\"user\"", "email=?", UserRepositoryJpaSpringDataTestFixture.user().email());
    }

    @Test
    public void shouldFindByEmail() {
        final Optional<User> foundUser = userRepositoryJpaSpringData.findByEmail(UserRepositoryJpaSpringDataTestFixture.user().email());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get()).isEqualToComparingFieldByFieldRecursively(UserRepositoryJpaSpringDataTestFixture.user());
    }

    @Test
    public void shouldNotFindByEmail() {
        final Optional<User> foundUser = userRepositoryJpaSpringData.findByEmail("invalid@email.com");

        assertThat(foundUser).isNotPresent();
    }
}
