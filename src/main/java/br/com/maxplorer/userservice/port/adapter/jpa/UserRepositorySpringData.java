package br.com.maxplorer.userservice.port.adapter.jpa;

import br.com.maxplorer.userservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositorySpringData extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
