package io.ram.openbanking.model;

import io.ram.openbanking.repository.CardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardTest {

    private static final Long CARD_NUMBER = 12345678990L;
    private static final Integer CARD_CVV = 123;
    @Autowired
    private CardRepository cardRepository;

    private Card card;

    @Before
    public void setup(){

        card = new Card(CARD_NUMBER,CARD_CVV);

    }


    @Test
    public void test_create_new_card(){
        Card savedCard = cardRepository.save(card);
        assertEquals(card,savedCard);
    }

}
