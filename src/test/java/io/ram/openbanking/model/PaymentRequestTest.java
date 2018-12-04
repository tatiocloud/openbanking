package io.ram.openbanking.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentRequestTest {

    private static final String USERNAME = "testuser";
    private static final Long CARD_NUMBER = 12345678990L;
    private static final Integer CARD_CVV = 123;
    private Double amountToBeAddedToBalance = 1000.00;



    @Test
    public void test_json_to_PaymentRequestObject() throws IOException {
        PaymentRequest obj = new PaymentRequest(USERNAME,CARD_NUMBER,CARD_CVV,amountToBeAddedToBalance);
        String json = obj.mapToJson(obj);
        PaymentRequest reqObj = obj.mapFromJson(json, PaymentRequest.class);
        assertEquals(obj.getNumber(),reqObj.getNumber());
        assertEquals(obj.getUsername(),reqObj.getUsername());
    }

}
