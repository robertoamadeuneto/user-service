package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.User;
import br.com.maxplorer.userservice.core.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class UserRepositoryJpa implements UserRepository {

    private final UserRepositoryJpaSpringData userRepositoryJpaSpringData;

    @Override
    public Optional<User> findById(final UUID id) {
        return userRepositoryJpaSpringData.findById(id);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepositoryJpaSpringData.findByEmail(email);
    }

    @Override
    public void save(final User user) {
        synchronizeBidirectionalMapping(user);
        userRepositoryJpaSpringData.save(user);
    }

    private void synchronizeBidirectionalMapping(final User user) {
        user.passwords().forEach(p -> p.user(user));
    }
}
