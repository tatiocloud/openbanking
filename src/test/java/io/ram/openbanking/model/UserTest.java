package io.ram.openbanking.model;

import io.ram.openbanking.model.User;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    public static final String USERNAME = "testuser";
    public static final String PASSWORD = "password";
    public static final Date CREATE_DT = Date.valueOf(LocalDate.now());
    public static final Timestamp LAST_LOGGED_IN = Timestamp.from(Instant.now());

    private User user;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup(){
        userRepository.deleteAll();

        user = new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN);
    }

    @Test
    public void test_new_user_creation(){

        User savedUser = userRepository.save(user);
        Assert.assertEquals(user, savedUser);

    }

    @Test
    public void test_new_user_creation_and_query_user(){
        User user = new User(USERNAME,
                        PASSWORD,
                        CREATE_DT,
                        LAST_LOGGED_IN);
        User savedUser = userRepository.save(user);

        User queriedUser = userRepository.findByUsername(savedUser.getUsername()).get(0);
        Assert.assertEquals(user,queriedUser);

    }


}
