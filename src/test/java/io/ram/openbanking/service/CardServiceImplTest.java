package io.ram.openbanking.service;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.model.UserBalance;
import io.ram.openbanking.repository.CardRepository;
import io.ram.openbanking.repository.UserBalanceRepository;
import io.ram.openbanking.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceImplTest {

    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "password";
    private static final Date CREATE_DT = Date.valueOf(LocalDate.now());
    private static final Timestamp LAST_LOGGED_IN = Timestamp.from(Instant.now());
    private User user;

    private static final Long CARD_NUMBER = 12345678990L;
    private static final Integer CARD_CVV = 123;
    private Card card;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;



    @Before
    public void setup(){
        cardRepository.deleteAll();
        userRepository.deleteAll();
    }

    private User createUser() {
        return new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN);
    }

    public Card createNewUserCard(){

        user = createUser();
        User savedUser = userRepository.save(user);
        return cardRepository.save(new Card(CARD_NUMBER,CARD_CVV,savedUser));
    }

    @Test
    public void test_create_new_card(){
        userRepository.deleteAll();
        card = createNewUserCard();
        Card savedCard = cardService.addNewCard(card);
        assertEquals(this.card,savedCard);
    }

    @Test
    public void test_create_check_usercard_details(){
        card = createNewUserCard();
        Card savedCard = cardRepository.save(card);

        assertEquals(card,savedCard);
    }

}
