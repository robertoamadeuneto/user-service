package br.com.maxplorer.userservice.application.user;

import br.com.maxplorer.userservice.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.domain.exception.UserEmailAlreadyExistsException;
import br.com.maxplorer.userservice.domain.exception.constraint.UserEmailAlreadyExistsConstraint;
import br.com.maxplorer.userservice.domain.user.Genre;
import br.com.maxplorer.userservice.domain.user.User;
import br.com.maxplorer.userservice.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserApplicationService {

    private final UserRepository userRepository;

    @Autowired
    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID registerNewUser(NewUserCommand command) {

        if (userRepository.findByEmail(command.email()).isPresent()) {
            throw new UserEmailAlreadyExistsException(new UserEmailAlreadyExistsConstraint("email", command.email()));
        }

        final User user = User.newUser(userRepository.newId(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.dateOfBirth(),
                Genre.valueOf(command.genre().name()),
                command.email(),
                command.password());

        userRepository.save(user);

        return user.id();
    }
}
