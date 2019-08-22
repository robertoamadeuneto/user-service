package br.com.maxplorer.userservice.core.application.user.query;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserQueryTest {

    @Test
    public void shouldReturnUserQuery() {

        final UserQuery userQuery = UserQuery.from(UserQueryTestFixture.user());

        assertThat(userQuery).isEqualToComparingFieldByFieldRecursively(UserQueryTestFixture.userQuery());
    }
}
