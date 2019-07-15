package br.com.maxplorer.userservice.port.adapter.jpa;

import br.com.maxplorer.userservice.domain.user.User;
import br.com.maxplorer.userservice.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryJpa implements UserRepository {

    private UserRepositorySpringData userRepositorySpringData;

    @Autowired
    public UserRepositoryJpa(UserRepositorySpringData userRepositorySpringData) {
        this.userRepositorySpringData = userRepositorySpringData;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositorySpringData.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepositorySpringData.save(user);
    }
}
