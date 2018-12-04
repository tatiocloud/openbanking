package io.ram.openbanking.controller;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.service.CardService;
import io.ram.openbanking.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    Card card;

    @Test
    public void create_new_card() throws Exception {

        String USERNAME = "testuser";
        String PASSWORD = "password";
        Date CREATE_DT = Date.valueOf(LocalDate.now());
        Timestamp LAST_LOGGED_IN = Timestamp.from(Instant.now());

        User user = new User(USERNAME,PASSWORD,CREATE_DT,LAST_LOGGED_IN);

        String userToJson = super.mapToJson(user);
        when(userService.addNewUser(anyString(),anyString())).thenReturn(user);
        this.mockMvc
                .perform(post("/api/users/create").contentType(MediaType.APPLICATION_JSON).content(userToJson))
                .andExpect(jsonPath("$.username",USERNAME).hasJsonPath())
                .andExpect(status().isOk())
                .andReturn();
    }

}