package io.ram.openbanking.controller;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.service.CardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest extends AbstractTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @Mock
    Card card;

    @Test
    public void create_new_card() throws Exception {
        Long CARD_NUMBER = 12345678990L;
        Integer CARD_CVV = 123;

        String USERNAME = "testuser";
        String PASSWORD = "password";
        Date CREATE_DT = Date.valueOf(LocalDate.now());
        Timestamp LAST_LOGGED_IN = Timestamp.from(Instant.now());

        User user = new User(USERNAME,PASSWORD,CREATE_DT,LAST_LOGGED_IN);
        card = new Card(CARD_NUMBER,CARD_CVV,user);
        long number = 1234567890L;

        String cardToJson = super.mapToJson(card);
        when(cardService.addNewCard(ArgumentMatchers.any(Card.class))).thenReturn(card);
        this.mockMvc
                .perform(post("/api/cards/create").contentType(MediaType.APPLICATION_JSON).content(cardToJson))
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$.number",number).hasJsonPath())
                .andExpect(status().isOk())
                .andReturn();
    }

}