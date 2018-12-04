package io.ram.openbanking.controller;

import io.ram.openbanking.model.*;
import io.ram.openbanking.service.BalanceService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest extends AbstractTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BalanceService balanceService;

    @MockBean
    CardService cardService;

    @MockBean
    private UserService userService;

    @InjectMocks
    PaymentController paymentController;

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
        Double amountToBeAdded = 1000.00;

        User user = new User(USERNAME,PASSWORD,CREATE_DT,LAST_LOGGED_IN);
        card = new Card(CARD_NUMBER,CARD_CVV,user);
        long number = 1234567890L;

        when(userService.findByUserName(any(String.class))).thenReturn(user);
        when(cardService.findCardByNumberAndCvv(CARD_NUMBER,CARD_CVV)).thenReturn(card);
        UserBalance userBalance = new UserBalance(user,card);
        userBalance.transactCharge(amountToBeAdded);

        when(balanceService.charge(any(User.class),any(Card.class),any(Double.class))).thenReturn(userBalance);

        PaymentRequest paymentRequest = new PaymentRequest(USERNAME,CARD_NUMBER,CARD_CVV,amountToBeAdded,0.0);

        PaymentResponse response = new PaymentResponse(amountToBeAdded);

        String userBalToJson = super.mapToJson(userBalance);
        String responseJson = super.mapToJson(response);

        this.mockMvc
                .perform(post("/api/payment/charge").contentType(MediaType.APPLICATION_JSON).content(userBalToJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson))
                .andExpect(jsonPath("$.balance",amountToBeAdded).hasJsonPath())
                .andReturn();
    }

}