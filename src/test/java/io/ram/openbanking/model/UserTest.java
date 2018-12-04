package io.ram.openbanking.model;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "password";
    private static final Date CREATE_DT = Date.valueOf(LocalDate.now());
    private static final Timestamp LAST_LOGGED_IN = Timestamp.from(Instant.now());

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Before
    public  void setup(){
        user = userRepository.save(new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN));
    }

    @After
    public  void teardown(){
        userRepository.deleteAll(userRepository.findAll());
    }

    @Test
    public void test_new_user_creation(){
        Assert.assertEquals(user.getUsername(),USERNAME);
    }

    @Test
    public void test_new_user_creation_and_query_user(){

        User queriedUser = userRepository.findByUsername(user.getUsername());
        Assert.assertEquals(user,queriedUser);
    }
}
