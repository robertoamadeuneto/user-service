package br.com.maxplorer.userservice.port.adapter.jpa;

import br.com.maxplorer.userservice.domain.user.User;
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
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositorySpringDataTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserRepositorySpringData userRepositorySpringData;

    @Before
    public void setUp() {
        transactionTemplate.execute((TransactionCallback<User>) status -> {
            userRepositorySpringData.save(UserRepositorySpringDataTestFixture.user());
            return null;
        });
    }

    @After
    public void tearDown() {
        JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "\"user\"", "email=?", UserRepositorySpringDataTestFixture.email());
    }

    @Test
    public void shouldFindByEmail() {

        final Optional<User> user = userRepositorySpringData.findByEmail(UserRepositorySpringDataTestFixture.email());

        assertThat(user).isPresent();
        assertThat(user.get()).isEqualToComparingFieldByFieldRecursively(UserRepositorySpringDataTestFixture.user());
    }
}
