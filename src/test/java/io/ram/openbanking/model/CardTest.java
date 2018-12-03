package io.ram.openbanking.model;

import io.ram.openbanking.repository.CardRepository;
import io.ram.openbanking.repository.UserRepository;
import org.junit.After;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardTest {

    private static final Long CARD_NUMBER = 12345678990L;
    private static final Integer CARD_CVV = 123;

    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "password";
    private static final Date CREATE_DT = Date.valueOf(LocalDate.now());
    private static final Timestamp LAST_LOGGED_IN = Timestamp.from(Instant.now());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    private Card card;
    private User user;

    @Before
    public void setup(){
        user = userRepository.save(createUser());
    }

    @After
    public void teardown(){
        cardRepository.deleteAll();
        userRepository.deleteAll();
    }

    private User createUser() {
        return new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN);
    }

    @Test
    public void test_create_new_card(){
        //Given
        card = new Card(CARD_NUMBER,CARD_CVV,user);
        //When
        Card savedCard = cardRepository.save(card);
        //Then
        assertEquals(savedCard.getUser(),user);
        assertEquals(card,savedCard);
    }

}
