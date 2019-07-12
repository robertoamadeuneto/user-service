package br.com.maxplorer.userservice.port.adapter.rest;

import br.com.maxplorer.userservice.application.user.UserApplicationService;
import br.com.maxplorer.userservice.application.user.command.NewUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> registerNewUser(@RequestBody NewUserCommand newUserCommand) {

        final UUID newUserId = userApplicationService.registerNewUser(newUserCommand);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(newUserId)
                .toUri())
                .build();
    }
}
