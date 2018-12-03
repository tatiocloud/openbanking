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
public class UserServiceImplTest {

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
    UserService userService;

    @Before
    public void setup(){
        userRepository.deleteAll();
    }

    private User createUser() {
        return new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN);
    }


    @Test
    public void test_create_new_user(){

        User byUsername = userRepository.findByUsername(USERNAME);
        if(byUsername == null){
            User savedUser = userService.addNewUser(USERNAME,PASSWORD);
            assertEquals(savedUser.getUsername(), USERNAME);
            assertEquals(savedUser.getPassword(), PASSWORD);
        }
    }
}
