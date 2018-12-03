package io.ram.openbanking.model;

import io.ram.openbanking.repository.CardRepository;
import io.ram.openbanking.repository.UserBalanceRepository;
import io.ram.openbanking.repository.UserRepository;
import org.junit.After;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBalanceTest {

    private static final Long CARD_NUMBER = 12345678990L;
    private static final Integer CARD_CVV = 123;

    public static final String USERNAME = "testuser";
    public static final String PASSWORD = "password";
    public static final Date CREATE_DT = Date.valueOf(LocalDate.now());
    public static final Timestamp LAST_LOGGED_IN = Timestamp.from(Instant.now());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserBalanceRepository userBalanceRepository;


    private Card card;

    private User user;


    @Before
    public void setup(){

        user = new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN);

        userRepository.save(user);

        card = new Card(CARD_NUMBER,CARD_CVV,user);
        cardRepository.save(card);
    }

    @After
    public void teardown(){

        userBalanceRepository.deleteAll();
        cardRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void test_user_add_balance(){

        UserBalance balance = new UserBalance(user, card);
        Double amount = 1000.00;
        balance.transactCharge(amount);

        UserBalance userBalance = userBalanceRepository.save(balance);
        Assert.assertEquals(amount,userBalance.getBalance());
    }

    @Test
    public void test_user_deduct_balance(){

        UserBalance balance = new UserBalance(user, card);
        Double amount = 1000.00;
        balance.transactCharge(amount);

        UserBalance userBalance = userBalanceRepository.save(balance);
        Assert.assertEquals(amount,userBalance.getBalance());

        User byUsername = userRepository.findByUsername(user.getUsername());//.get(0);
        Card byNumberAndCvv = cardRepository.findByNumberAndCvv(card.getNumber(), card.getCvv()).get(0);

        UserBalance queriedUserBalanceWithUsernameAndCardNumber = userBalanceRepository.findByUserAndCard(byUsername, byNumberAndCvv);
        Double deductAmt = 100.00;
        queriedUserBalanceWithUsernameAndCardNumber.transactCredit(deductAmt);
        UserBalance deductedUserBalance = userBalanceRepository.save(queriedUserBalanceWithUsernameAndCardNumber);

        Assert.assertEquals(Double.valueOf(900.0),deductedUserBalance.getBalance());
    }


}
