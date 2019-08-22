package br.com.maxplorer.userservice.core.domain.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    default UUID newId() {
        return UUID.randomUUID();
    }

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    void save(User user);
}
