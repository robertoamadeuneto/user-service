package br.com.maxplorer.userservice.adapter.rest.controller;

import br.com.maxplorer.userservice.core.application.user.UserApplicationService;
import br.com.maxplorer.userservice.core.application.user.command.NewUserCommand;
import br.com.maxplorer.userservice.core.application.user.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<Void> registerNewUser(@RequestBody NewUserCommand command) {

        final UUID newUserId = userApplicationService.registerNewUser(command);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{newUserId}")
                .buildAndExpand(newUserId)
                .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQuery> findUserById(@PathVariable UUID id) {

        final UserQuery query = userApplicationService.findUserById(id);

        return ResponseEntity.ok(query);
    }
}
