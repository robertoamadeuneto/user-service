package br.com.constock.userservice.port.adapter.rest;

import br.com.constock.userservice.application.user.UserApplicationService;
import br.com.constock.userservice.domain.exception.UserEmailAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        mockMvc.perform(post(UserController.class.getAnnotation(RequestMapping.class).value()[0])
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserControllerTestFixture.newUserCommand())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnHttpStatus400WhenUserEmailAlreadyExists() throws Exception {

        when(userApplicationService.registerNewUser(any())).thenThrow(UserEmailAlreadyExistsException.class);

        mockMvc.perform(post(UserController.class.getAnnotation(RequestMapping.class).value()[0])
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserControllerTestFixture.newUserCommand())))
                .andExpect(status().isBadRequest());
    }
}
