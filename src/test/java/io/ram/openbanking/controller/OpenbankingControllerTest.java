package io.ram.openbanking.controller;

import io.ram.openbanking.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;


@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(OpenbankingController.class)
public class OpenbankingControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenbankingController openbankingController;

    @Test
    public void test_new_user_creation(){

        User user = new User("username","password", Date.from(Instant.now()), Timestamp.from(Instant.now()));


    }

}