package br.com.maxplorer.userservice.adapter.jpa.user;

import br.com.maxplorer.userservice.core.domain.user.User;
import br.com.maxplorer.userservice.core.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryJpa implements UserRepository {

    private UserRepositoryJpaSpringData userRepositoryJpaSpringData;

    @Autowired
    public UserRepositoryJpa(UserRepositoryJpaSpringData userRepositoryJpaSpringData) {
        this.userRepositoryJpaSpringData = userRepositoryJpaSpringData;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepositoryJpaSpringData.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryJpaSpringData.findByEmail(email);
    }

    @Override
    public void save(User user) {
        synchronizeBidirectionalMapping(user);
        userRepositoryJpaSpringData.save(user);
    }

    private void synchronizeBidirectionalMapping(User user) {
        user.passwords().forEach(p -> p.user(user));
    }
}
