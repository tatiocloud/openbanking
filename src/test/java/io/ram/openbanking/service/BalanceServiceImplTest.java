package io.ram.openbanking.service;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.model.UserBalance;
import io.ram.openbanking.repository.CardRepository;
import io.ram.openbanking.repository.UserBalanceRepository;
import io.ram.openbanking.repository.UserRepository;
import org.junit.Assert;
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
public class BalanceServiceImplTest {

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
    UserBalanceRepository userBalanceRepository;

    @Autowired
    BalanceService balanceService;


    @Before
    public void setup(){
        userRepository.deleteAll();
        cardRepository.deleteAll();
    }

    private User createUser() {
        return new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN);
    }

    public Card createCard(){

        return new Card(CARD_NUMBER,CARD_CVV,user);
    }

    @Test
    public void test_create_new_user(){

        user = createUser();
        User savedUser = userRepository.save(user);
        assertEquals(user,savedUser);
    }

    @Test
    public void test_create_new_card(){
        card = createCard();
        Card savedCard = balanceService.addNewCard(this.card);
        assertEquals(this.card,savedCard);
    }

    @Test
    public void test_create_check_usercard_details(){
        card = createCard();
        Card savedCard = cardRepository.save(card);

        assertEquals(card,savedCard);
    }

    @Test
    public void test_charge_card_with_1000(){

        user = createUser();
        User savedUser = userRepository.save(user);

        card = createCard();
        Card savedCard = cardRepository.save(card);

        UserBalance userBalance = new UserBalance(user, card);
        UserBalance initialBalance = userBalanceRepository.save(userBalance);

        assertEquals(Double.valueOf(0.0),initialBalance.getBalance());

        UserBalance updatedBalance = balanceService.charge(user, card, 1000.00);
        assertEquals(updatedBalance.getBalance(),Double.valueOf(1000.00));

    }
}
