package br.com.maxplorer.userservice.core.application.user;

import br.com.maxplorer.userservice.core.application.user.command.AuthenticateUserCommand;
import br.com.maxplorer.userservice.core.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.core.application.user.query.UserQuery;
import br.com.maxplorer.userservice.core.domain.exception.UserEmailAlreadyExistsException;
import br.com.maxplorer.userservice.core.domain.exception.UserNotActiveException;
import br.com.maxplorer.userservice.core.domain.exception.UserNotFoundException;
import br.com.maxplorer.userservice.core.domain.exception.WrongEmailOrPasswordException;
import br.com.maxplorer.userservice.core.domain.exception.constraint.UserEmailAlreadyExistsConstraint;
import br.com.maxplorer.userservice.core.domain.user.Genre;
import br.com.maxplorer.userservice.core.domain.user.User;
import br.com.maxplorer.userservice.core.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserApplicationService {

    private final UserRepository userRepository;

    public UUID registerNewUser(final NewUserCommand command) {
        userRepository.findByEmail(command.email()).ifPresent(u -> {
            throw new UserEmailAlreadyExistsException(new UserEmailAlreadyExistsConstraint("email", u.email()));
        });

        final User user = User.newUser(userRepository.newId(),
                command.firstName(),
                command.lastName(),
                command.dateOfBirth(),
                Genre.valueOf(command.genre().name()),
                command.email(),
                command.password());

        userRepository.save(user);

        return user.id();
    }

    public UserQuery findUserById(final UUID id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        return UserQuery.from(user);
    }

    public void activateUser(final UUID id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        user.activate();

        userRepository.save(user);
    }

    public UserQuery authenticateUser(final AuthenticateUserCommand command) {
        final Optional<User> user = userRepository.findByEmail(command.email());

        if (!user.isPresent() || !user.get().isPasswordValid(command.password())) {
            throw new WrongEmailOrPasswordException();
        } else if (!user.get().isActive()) {
            throw new UserNotActiveException(command.email());
        }

        return UserQuery.from(user.get());
    }
}
