package br.com.maxplorer.userservice.adapter.rest;

import br.com.maxplorer.userservice.adapter.rest.controller.UserController;
import br.com.maxplorer.userservice.core.application.user.UserApplicationService;
import br.com.maxplorer.userservice.core.domain.exception.UserEmailAlreadyExistsException;
import br.com.maxplorer.userservice.core.domain.exception.UserNotFoundException;
import br.com.maxplorer.userservice.core.domain.exception.constraint.UserEmailAlreadyExistsConstraint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserApplicationService userApplicationService;

    @Test
    public void shouldRegisterNewUser() throws Exception {

        when(userApplicationService.registerNewUser(any())).thenReturn(UUID.fromString("8089c74f-c660-4c68-9697-4a03144b8e13"));

        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserControllerTestFixture.newUserCommand())))
                .andExpect(status().isCreated());

        verify(userApplicationService).registerNewUser(eq(UserControllerTestFixture.newUserCommand()));
    }

    @Test
    public void shouldReturnHttpStatus400WhenTryingToRegisterNewUserAndUserEmailAlreadyExists() throws Exception {

        when(userApplicationService.registerNewUser(any()))
                .thenThrow(new UserEmailAlreadyExistsException(new UserEmailAlreadyExistsConstraint("email", UserControllerTestFixture.email())));

        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserControllerTestFixture.newUserCommand())))
                .andExpect(status().isConflict());

        verify(userApplicationService).registerNewUser(eq(UserControllerTestFixture.newUserCommand()));
    }

    @Test
    public void shouldFindUserById() throws Exception {

        when(userApplicationService.findUserById(any())).thenReturn(UserControllerTestFixture.userQuery());

        mockMvc.perform(get("/v1/users/" + UserControllerTestFixture.id())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(UserControllerTestFixture.userQuery())));

        verify(userApplicationService).findUserById(eq(UserControllerTestFixture.id()));
    }

    @Test
    public void shouldReturnHttpStatus404WhenTryingToFindUserByIdAndUserWasNotFound() throws Exception {

        when(userApplicationService.findUserById(any())).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/v1/users/" + UserControllerTestFixture.id())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userApplicationService).findUserById(eq(UserControllerTestFixture.id()));
    }

    @Test
    public void shouldActivateUserById() throws Exception {

        doNothing().when(userApplicationService).activateUser(any());

        mockMvc.perform(post("/v1/users/" + UserControllerTestFixture.id() + "/activation")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userApplicationService).activateUser(eq(UserControllerTestFixture.id()));
    }

    @Test
    public void shouldReturnHttpStatus404WhenTryingToActivateUserAndUserWasNotFound() throws Exception {

        doThrow(UserNotFoundException.class).when(userApplicationService).activateUser(any());

        mockMvc.perform(post("/v1/users/" + UserControllerTestFixture.id() + "/activation")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userApplicationService).activateUser(eq(UserControllerTestFixture.id()));
    }
}
