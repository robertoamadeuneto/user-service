package br.com.maxplorer.userservice.adapter.rest.controller;

import br.com.maxplorer.userservice.core.application.user.UserApplicationService;
import br.com.maxplorer.userservice.core.application.user.command.AuthenticateUserCommand;
import br.com.maxplorer.userservice.core.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.core.application.user.query.UserQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<Void> registerNewUser(@RequestBody final NewUserCommand command) {
        final UUID newUserId = userApplicationService.registerNewUser(command);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{newUserId}")
                .buildAndExpand(newUserId)
                .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQuery> findUserById(@PathVariable final UUID id) {
        final UserQuery query = userApplicationService.findUserById(id);

        return ResponseEntity.ok(query);
    }

    @PostMapping("/{id}/activation")
    public ResponseEntity<Void> activateUser(@PathVariable final UUID id) {
        userApplicationService.activateUser(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/authentication")
    public ResponseEntity<UserQuery> authenticateUser(@RequestBody final AuthenticateUserCommand command) {
        final UserQuery query = userApplicationService.authenticateUser(command);

        return ResponseEntity.ok(query);
    }
}
